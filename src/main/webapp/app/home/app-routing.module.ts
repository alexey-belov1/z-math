import {Injectable, NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ContactsPageComponent} from '../entities/contacts-page/contacts-page.component';
import {RulesPageComponent} from '../entities/rules-page/rules-page.component';
import {AuthGuard} from '../shared/guards/auth.guard';

import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import {Observable} from "rxjs";
import {TaskService} from "../shared/services/task.service";
import {HttpResponse} from "@angular/common/http";
import {map} from "rxjs/operators";
import {HomePageComponent} from "../entities/home-page/home-page.component";
import {NewTaskPageComponent} from "../entities/new-task-page/new-task-page.component";
import {RegistrationPageComponent} from "../entities/registration-page/registration-page.component";
import {PasswordRecoveryPageComponent} from "../entities/password-recovery-page/password-recovery-page.component";
import {NoAuthorizationPageComponent} from "../entities/no-authorization-page/no-authorization-page.component";
import {ReviewsPageComponent} from "../entities/reviews-page/reviews-page.component";
import {TasksPageComponent} from "../entities/tasks-page/tasks-page.component";
import {MainLayoutComponent} from "../layouts/main-layout/main-layout.component";
import {ITask} from "../shared/model/task.model";

@Injectable({ providedIn: 'root' })
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

@Injectable({ providedIn: 'root' })
export class DataResolver {
    constructor(private taskService: TaskService) {}

    resolve(): Observable<any[]> {
        return this.taskService.query().pipe(
            map((res: HttpResponse<ITask[]>) => {
                return res.body ? res.body : [];
            })
        );
    }
}


const routes: Routes = [
  {
    path: '', component: MainLayoutComponent, children: [
      {path: '', redirectTo: '/', pathMatch: 'full'},
      {path: '', component: HomePageComponent},
      {path: 'contacts', component: ContactsPageComponent},
      {path: 'new-task', component: NewTaskPageComponent, canActivate: [AuthGuard]},
/*      {path: 'payment', component: PaymentPageComponent, canActivate: [AuthGuard]},*/
      {path: 'registration', component: RegistrationPageComponent},
      {path: 'reviews', component: ReviewsPageComponent},
      {path: 'rules', component: RulesPageComponent},
      {
          path: 'tasks',
          component: TasksPageComponent,
          resolve: {
              pagingParams: ResolvePagingParams,
              data: DataResolver
          },
      },
      {path: 'password-recovery', component: PasswordRecoveryPageComponent},
      {path: 'no-authorization', component: NoAuthorizationPageComponent}
    ]
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
