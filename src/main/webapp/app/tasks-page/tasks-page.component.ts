import {Component, ComponentFactoryResolver, OnInit, ViewChild} from '@angular/core';
import {TaskService} from '../shared/services/task.service';
import {ITask} from '../shared/interfaces';
import {animate, style, transition, trigger} from '@angular/animations';
import {TaskDetailComponent} from "./task-detail/task-detail.component";
import {RefDirective} from "../shared/ref.directive";
import {HttpHeaders, HttpParams, HttpResponse} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

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
export class TasksPageComponent implements OnInit {

    @ViewChild(RefDirective, {static: false}) refDir: RefDirective

    tasks: ITask[] = [];
    toggle: boolean[] = [];

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

    constructor(
        private taskService: TaskService,
        private resolver: ComponentFactoryResolver,
        private router: Router,
        protected activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.ascending = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
            this.loadPage();
        });

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
        const modalFactory = this.resolver.resolveComponentFactory(TaskDetailComponent)
        this.refDir.containerRef.clear()

        const component = this.refDir.containerRef.createComponent(modalFactory)

        component.instance.task = task;
        component.instance.close.subscribe(() => {
            this.refDir.containerRef.clear()
        })
    }

    loadPage(page?: number): void {
        let options: HttpParams = new HttpParams();

        let pageToLoad = page ? page : this.page;
        options = options.set('page', (pageToLoad - 1).toString());
        options = options.set('size', this.itemsPerPage.toString());

        let sort = this.predicate + ',' + (this.ascending ? 'asc' : 'desc');
        options = options.append('sort', sort);


        this.taskService.query(options).subscribe(
            (res: HttpResponse<ITask[]>) => this.onSuccess(res.body, res.headers, pageToLoad)
            /*,() => this.onError()*/
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
}
