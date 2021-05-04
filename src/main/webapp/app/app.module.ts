import { BrowserModule } from '@angular/platform-browser';
import {NgModule, Provider} from '@angular/core';

import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MenuComponent } from './shared/components/menu/menu.component';
import { HeaderComponent } from './shared/components/header/header.component';
import { FooterComponent } from './shared/components/footer/footer.component';
import { MainLayoutComponent } from './shared/components/main-layout/main-layout.component';
import { ContactsPageComponent } from './contacts-page/contacts-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { NewTaskPageComponent } from './new-task-page/new-task-page.component';
import { PaymentPageComponent } from './payment-page/payment-page.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { ReviewsPageComponent } from './reviews-page/reviews-page.component';
import { RulesPageComponent } from './rules-page/rules-page.component';
import { TasksPageComponent } from './tasks-page/tasks-page.component';
import {AppRoutingModule} from './app-routing.module';
import { LoginPanelComponent } from './shared/components/login-panel/login-panel.component';
import { PasswordRecoveryPageComponent } from './password-recovery-page/password-recovery-page.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from './shared/auth.interceptor';
import { NoAuthorizationPageComponent } from './no-authorization-page/no-authorization-page.component';
import { InfoComponent } from './tasks-page/info/info.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ItemMenuComponent } from './shared/components/menu/item-menu/item-menu.component';
import { NewReviewComponent } from './reviews-page/new-review/new-review.component';
import { AlertErrorComponent } from './shared/components/alert-error/alert-error.component';

const INTERCEPTOR_PROVIDER: Provider = {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptor,
  multi: true
};

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
    InfoComponent,
    ItemMenuComponent,
    NewReviewComponent,
    AlertErrorComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [INTERCEPTOR_PROVIDER],
  bootstrap: [AppComponent]
})
export class AppModule { }
