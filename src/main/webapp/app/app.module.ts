import {BrowserModule} from '@angular/platform-browser';
import {LOCALE_ID, NgModule} from '@angular/core';
import '@angular/common/locales/global/ru';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MenuComponent} from './shared/components/menu/menu.component';
import {HeaderComponent} from './shared/components/header/header.component';
import {FooterComponent} from './shared/components/footer/footer.component';
import {MainLayoutComponent} from './shared/components/main-layout/main-layout.component';
import {ContactsPageComponent} from './contacts-page/contacts-page.component';
import {HomePageComponent} from './home-page/home-page.component';
import {NewTaskPageComponent} from './new-task-page/new-task-page.component';
import {PaymentPageComponent} from './payment-page/payment-page.component';
import {RegistrationPageComponent} from './registration-page/registration-page.component';
import {ReviewsPageComponent} from './reviews-page/reviews-page.component';
import {RulesPageComponent} from './rules-page/rules-page.component';
import {TasksPageComponent} from './tasks-page/tasks-page.component';
import {AppRoutingModule} from './app-routing.module';
import {LoginPanelComponent} from './shared/components/login-panel/login-panel.component';
import {PasswordRecoveryPageComponent} from './password-recovery-page/password-recovery-page.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './shared/auth.interceptor';
import {NoAuthorizationPageComponent} from './no-authorization-page/no-authorization-page.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ItemMenuComponent} from './shared/components/menu/item-menu/item-menu.component';
import {NewReviewComponent} from './reviews-page/new-review/new-review.component';
import {AlertErrorComponent} from './shared/components/alert-error/alert-error.component';
import {ClickOutsideModule} from "ng-click-outside";
import {ErrorHandlerInterceptor} from "./shared/errorhandler.interceptor";
import {NotificationInterceptor} from "./shared/notification.interceptor";
import {TooltipDirective} from "./new-task-page/tooltip.directive";
import { TaskDetailComponent } from './tasks-page/task-detail/task-detail.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { TaskEditCostComponent } from './tasks-page/task-edit-cost/task-edit-cost.component';
import { TaskEditRefuseComponent } from './tasks-page/task-edit-refuse/task-edit-refuse.component';
import { TaskEditSolveComponent } from './tasks-page/task-edit-solve/task-edit-solve.component';
import {HasAnyRoleDirective} from "./shared/has-any-role.directive";
import { TaskEditPaymentComponent } from './tasks-page/task-edit-payment/task-edit-payment.component';

@NgModule({
    declarations: [
        AppComponent,
        MenuComponent,
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
        ItemMenuComponent,
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
