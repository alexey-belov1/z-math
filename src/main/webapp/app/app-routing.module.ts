import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainLayoutComponent} from './shared/components/main-layout/main-layout.component';
import {HomePageComponent} from './home-page/home-page.component';
import {ReviewsPageComponent} from './reviews-page/reviews-page.component';
import {NewTaskPageComponent} from './new-task-page/new-task-page.component';
import {ContactsPageComponent} from './contacts-page/contacts-page.component';
import {PaymentPageComponent} from './payment-page/payment-page.component';
import {RegistrationPageComponent} from './registration-page/registration-page.component';
import {RulesPageComponent} from './rules-page/rules-page.component';
import {TasksPageComponent} from './tasks-page/tasks-page.component';
import {PasswordRecoveryPageComponent} from './password-recovery-page/password-recovery-page.component';
import {NoAuthorizationPageComponent} from './no-authorization-page/no-authorization-page.component';
import {AuthGuard} from './shared/auth.guard';

const routes: Routes = [
  {
    path: '', component: MainLayoutComponent, children: [
      {path: '', redirectTo: '/', pathMatch: 'full'},
      {path: '', component: HomePageComponent},
      {path: 'contacts', component: ContactsPageComponent},
      {path: 'new-task', component: NewTaskPageComponent, canActivate: [AuthGuard]},
      {path: 'payment', component: PaymentPageComponent, canActivate: [AuthGuard]},
      {path: 'registration', component: RegistrationPageComponent},
      {path: 'reviews', component: ReviewsPageComponent},
      {path: 'rules', component: RulesPageComponent},
      {path: 'tasks', component: TasksPageComponent},
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
