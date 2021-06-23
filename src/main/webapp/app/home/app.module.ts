import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import '@angular/common/locales/global/ru';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ContactsPageComponent} from '../entities/contacts-page/contacts-page.component';
import {RulesPageComponent} from '../entities/rules-page/rules-page.component';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from '../shared/interceptors/auth.interceptor';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ClickOutsideModule} from "ng-click-outside";
import {ErrorHandlerInterceptor} from "../shared/interceptors/errorhandler.interceptor";
import {NotificationInterceptor} from "../shared/interceptors/notification.interceptor";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HasAnyRoleDirective} from "../shared/directives/has-any-role.directive";
import {PaymentPageComponent} from "../entities/payment-page/payment-page.component";
import {RegistrationPageComponent} from "../entities/registration-page/registration-page.component";
import {PasswordRecoveryPageComponent} from "../entities/password-recovery-page/password-recovery-page.component";
import {NoAuthorizationPageComponent} from "../entities/no-authorization-page/no-authorization-page.component";
import {TooltipDirective} from "../entities/new-task-page/tooltip.directive";
import {HomePageComponent} from "../entities/home-page/home-page.component";
import {NewTaskPageComponent} from "../entities/new-task-page/new-task-page.component";
import {ReviewsPageComponent} from "../entities/reviews-page/reviews-page.component";
import {TasksPageComponent} from "../entities/tasks-page/tasks-page.component";
import {NewReviewComponent} from "../entities/reviews-page/new-review/new-review.component";
import {TaskDetailComponent} from "../entities/tasks-page/task-detail/task-detail.component";
import {TaskEditCostComponent} from "../entities/tasks-page/task-edit-cost/task-edit-cost.component";
import {TaskEditRefuseComponent} from "../entities/tasks-page/task-edit-refuse/task-edit-refuse.component";
import {TaskEditSolveComponent} from "../entities/tasks-page/task-edit-solve/task-edit-solve.component";
import {TaskEditPaymentComponent} from "../entities/tasks-page/task-edit-payment/task-edit-payment.component";
import {NavbarComponent} from "../layouts/navbar/navbar.component";
import {FooterComponent} from "../layouts/footer/footer.component";
import {MainLayoutComponent} from "../layouts/main-layout/main-layout.component";
import {LoginPanelComponent} from "../layouts/login-panel/login-panel.component";
import {NavbarItemComponent} from "../layouts/navbar/navbar-item/navbar-item.component";
import {AlertErrorComponent} from "../layouts/alert-error/alert-error.component";
import {HeaderComponent} from "../layouts/header/header.component";

@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        HeaderComponent,
        FooterComponent,
        MainLayoutComponent,
        ContactsPageComponent,
        HomePageComponent,
        NewTaskPageComponent,
        PaymentPageComponent,
        RegistrationPageComponent,
        ReviewsPageComponent,
        RulesPageComponent,
        TasksPageComponent,
        LoginPanelComponent,
        PasswordRecoveryPageComponent,
        NoAuthorizationPageComponent,
        NavbarItemComponent,
        NewReviewComponent,
        AlertErrorComponent,
        TooltipDirective,
        TaskDetailComponent,
        TaskEditCostComponent,
        TaskEditRefuseComponent,
        TaskEditSolveComponent,
        HasAnyRoleDirective,
        TaskEditPaymentComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        ClickOutsideModule,
        NgbModule
    ],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true
        },
        {
            provide: LOCALE_ID,
            useValue: 'ru'
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
