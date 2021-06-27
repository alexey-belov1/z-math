import {Component, ComponentFactoryResolver, OnDestroy, OnInit} from '@angular/core';
import {animate, style, transition, trigger} from '@angular/animations';
import {TaskDetailComponent} from "./task-detail/task-detail.component";
import {HttpHeaders, HttpParams, HttpResponse} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Subscription} from "rxjs";
import {TaskService} from "../../shared/services/task.service";
import {EventBusService} from "../../shared/services/event-bus.service";
import {ITask} from "../../shared/model/task.model";
import {ISubject} from "../../shared/model/subject.model";
import {SubjectService} from "../../shared/services/subject.service";
import {IStatus} from "../../shared/model/status.model";
import {StatusService} from "../../shared/services/status.service";

@Component({
    selector: 'app-tasks-page',
    templateUrl: './tasks-page.component.html',
    styleUrls: ['./tasks-page.component.scss'],
    animations: [
        trigger('long-enter', [
            transition(':enter', [
                style({opacity: 0.2}),
                animate(350)
            ])
        ])
    ]
})
export class TasksPageComponent implements OnInit, OnDestroy {

    tasks: ITask[] = [];

    toggle: boolean[] = [];

    subjectIdFilter: number = null;

    statusIdFilter: number = null;

    subjects: ISubject[] = [];

    statuses: IStatus[] = [];

    // Количество всех заявок
    totalItems: number;

    // Количество заявок на странице
    itemsPerPage: number = 20;

    // Номер страницы
    page: number;

    // Условия по которому сортировать
    predicate: string;

    // В кком порядке сортировать
    ascending: boolean;

    // Подписка на прием сообщений от сервера по веб сокету
    onTaskEditSub: Subscription;

    constructor(
        private taskService: TaskService,
        private resolver: ComponentFactoryResolver,
        private router: Router,
        protected activatedRoute: ActivatedRoute,
        protected modalService: NgbModal,
        private eventBusService: EventBusService,
        protected subjectService: SubjectService,
        protected statusService: StatusService
    ) {
    }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.ascending = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
            this.loadPage();
        });

        this.onTaskEditSub = this.eventBusService.on("taskEdit", () => this.loadPage());

        this.loadPage();
    }

    getAll(): void {
        this.taskService.getAll()
            .subscribe(tasks => {
                this.tasks = tasks;
                for (const task of this.tasks) {
                    this.toggle.push(true);
                }
                console.log(this.toggle);
            });
    }

    getItem(i: number): string {
        return 'item' + i;
    }

    changeToggle(i: number): void {
        this.toggle[i] = !this.toggle[i];
    }

    showModal(task: ITask) {
        const modalRef = this.modalService.open(TaskDetailComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.task = task;
    }

    loadPage(page?: number): void {
        let options: HttpParams = new HttpParams();

        let pageToLoad = page ? page : this.page;
        options = options.set('page', (pageToLoad - 1).toString());
        options = options.set('size', this.itemsPerPage.toString());

        let sort = this.predicate + ',' + (this.ascending ? 'asc' : 'desc');
        options = options.append('sort', sort);

        options = this.filter(options);

        this.taskService.query(options).subscribe(
            (res: HttpResponse<ITask[]>) => this.onSuccess(res.body, res.headers, pageToLoad)
            /*,() => this.onError()*/
        );

        this.subjectService.getAll().subscribe(
            (subjects) => this.subjects = subjects
        );

        this.statusService.getAll().subscribe(
            (statuses) => this.statuses = statuses
        );
    }

    protected onSuccess(data: ITask[] | null, headers: HttpHeaders, page: number): void {
        console.warn("headers", headers);
        this.totalItems = Number(headers.get('X-Total-Count'));
        this.page = page;
        this.router.navigate(['/tasks'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
            }
        });
        this.tasks = data ? data : [];
    }

    /*  toggle(el: string): void {
        document.getElementById(el).classList.toggle('visibility');
      }*/

    /*  contains(el: string): boolean {
        return document.getElementById(el).classList.contains('toggle');
      }*/

    ngOnDestroy(): void {
        this.onTaskEditSub.unsubscribe();
    }

    filter(options: HttpParams): HttpParams {
        if (this.subjectIdFilter) {
            options = options.set('subjectId.equals', this.subjectIdFilter.toString());
        }
        if (this.statusIdFilter) {
            options = options.set('statusId.equals', this.statusIdFilter.toString());
        }
        return options;
    }

    applyFilter(): void {
        this.loadPage();
    }

    clearFilter() {
        this.subjectIdFilter = null;
        this.statusIdFilter = null;
        this.loadPage();
    }
}
