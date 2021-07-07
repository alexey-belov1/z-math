import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from "@angular/router";
import {TasksPageComponent} from "./tasks-page.component";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class ResolvePagingParams implements Resolve<any> {
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
        const sort = route.queryParams['sort'];
        return {
            page: route.queryParams['page'] ? route.queryParams['page'] : '1',
            predicate: sort ? sort.split(',')[0] : 'id',
            ascending: false,
        };
    }
}

export const tasksPageRoute: Routes = [
    {path: '',
        component: TasksPageComponent,
        resolve: {
            pagingParams: ResolvePagingParams
        }
    }
];
