import {Component, ComponentFactoryResolver, OnDestroy, OnInit} from '@angular/core';
import {animate, style, transition, trigger} from '@angular/animations';
import {TaskDetailComponent} from "./task-detail/task-detail.component";
import {HttpHeaders, HttpParams} from "@angular/common/http";
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

    // Предыдущее значеие номера страницы. Используется чтоб не было повторной загрузки данных в начале
    previousPage: number;

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
            this.previousPage = this.page;
        });

        this.subjectService.findAll().subscribe(res => {
            this.subjects = res.body;
        });

        this.statusService.findAll().subscribe(res => {
            this.statuses = res.body
        });

        this.loadTasks();

        this.onTaskEditSub = this.eventBusService.on("taskEdit", () => this.loadTasks());
    }

    // Метод защищает от повторной загрузки данных при первом заходе на страницу
    loadPage(page: number): void {
        if (page != this.previousPage) {
            this.previousPage = page;
            this.loadTasks();
        }
    }

    loadTasks(): void {
        let options: HttpParams = new HttpParams();
        options = options.set('page', (this.page - 1).toString());
        options = options.set('size', this.itemsPerPage.toString());
        let sort = this.predicate + ',' + (this.ascending ? 'asc' : 'desc');
        options = options.append('sort', sort);
        options = this.filter(options);

        this.taskService.query(options).subscribe(res => {
            this.onSuccess(res.body, res.headers);
        });
    }

    private onSuccess(tasks: ITask[] | null, headers: HttpHeaders): void {
        this.totalItems = Number(headers.get('X-Total-Count'));
        this.router.navigate(['/tasks'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
            }
        }).then();
        this.tasks = tasks ? tasks : [];
    }

    showTaskDetailModal(task: ITask) {
        const modalRef = this.modalService.open(TaskDetailComponent, {size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.task = task;
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
        this.page = 1;
        this.loadTasks();
    }

    clearFilter() {
        this.subjectIdFilter = null;
        this.statusIdFilter = null;
        this.page = 1;
        this.loadTasks();
    }

    ngOnDestroy(): void {
        this.onTaskEditSub.unsubscribe();
    }
}
