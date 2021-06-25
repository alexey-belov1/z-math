import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from "@angular/router";
import {TasksPageComponent} from "./tasks-page.component";
import {Injectable} from "@angular/core";
import {TaskService} from "../../shared/services/task.service";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {HttpResponse} from "@angular/common/http";
import {ITask} from "../../shared/model/task.model";

@Injectable({providedIn: 'root'})
export class ResolvePagingParams implements Resolve<any> {
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
        const sort = route.queryParams['sort'];
        return {
            page: route.queryParams['page'] ? route.queryParams['page'] : '1',
            predicate: sort ? sort.split(',')[0] : 'id',
            ascending: true,
        };
    }
}

@Injectable({providedIn: 'root'})
export class DataResolver {
    constructor(private taskService: TaskService) {
    }

    resolve(): Observable<any[]> {
        return this.taskService.query().pipe(
            map((res: HttpResponse<ITask[]>) => {
                return res.body ? res.body : [];
            })
        );
    }
}

export const tasksPageRoute: Routes = [
    {path: '',
        component: TasksPageComponent,
        resolve: {
            pagingParams: ResolvePagingParams,
            data: DataResolver
        }}
];
