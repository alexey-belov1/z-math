import {LOCALE_ID, NgModule} from '@angular/core';
import '@angular/common/locales/global/ru';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthInterceptor} from '../shared/interceptors/auth.interceptor';
import {ErrorHandlerInterceptor} from "../shared/interceptors/errorhandler.interceptor";
import {NotificationInterceptor} from "../shared/interceptors/notification.interceptor";
import {NavbarComponent} from "../layouts/navbar/navbar.component";
import {FooterComponent} from "../layouts/footer/footer.component";
import {MainLayoutComponent} from "../layouts/main-layout/main-layout.component";
import {LoginPanelComponent} from "../layouts/login-panel/login-panel.component";
import {NavbarItemComponent} from "../layouts/navbar/navbar-item/navbar-item.component";
import {AlertErrorComponent} from "../layouts/alert-error/alert-error.component";
import {HeaderComponent} from "../layouts/header/header.component";
import {BrowserModule} from "@angular/platform-browser";
import {SharedModule} from "../shared/shared.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ClickOutsideModule} from "ng-click-outside";

@NgModule({
    declarations: [
        AppComponent,
        NavbarComponent,
        HeaderComponent,
        FooterComponent,
        MainLayoutComponent,
        LoginPanelComponent,
        NavbarItemComponent,
        AlertErrorComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        AppRoutingModule,
        ClickOutsideModule,
        SharedModule
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
