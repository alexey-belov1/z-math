import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from "../../shared/services/auth.service";
import {AlertService} from "../../shared/services/alert.service";
import {IUser} from "../../shared/model/user.model";

@Component({
    selector: 'app-login-panel',
    templateUrl: './login-panel.component.html',
    styleUrls: ['./login-panel.component.scss']
})
export class LoginPanelComponent implements OnInit {

    form: FormGroup;
    submitted = false;
    message: string;

    constructor(
        public authService: AuthService,
        private router: Router,
        private route: ActivatedRoute,
        private alertService: AlertService
    ) {
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            login: new FormControl(null, [Validators.required]),
            password: new FormControl(null, [Validators.required])
        });
    }

    logout(): void {
        this.authService.logout();
        this.router.navigate(['/']);
    }

    isAuthenticated(): boolean {
        return this.authService.isAuthenticated();
    }

    submit(): void {

        if (this.form.invalid) {
            this.form.markAllAsTouched();
            return;
        }

        if (this.form.invalid) {
            if (this.form.get('login').invalid && this.form.get('login').errors.required) {
                this.alertService.error('Необходимо заполнить поле "Логин"');
                return;
            }
            if (this.form.get('password').invalid && this.form.get('password').errors.required) {
                this.alertService.error('Необходимо заполнить поле "Пароль"');
                return;
            }
        }

        this.submitted = true;

        const user: IUser = {
            login: this.form.value.login,
            password: this.form.value.password
        };

        this.authService.login(user).subscribe(() => {
            this.form.reset();
            this.router.navigate(['/']);
            this.submitted = false;
        }, () => {
            this.submitted = false;
        });
    }

    markAllAsUntouched(): void {
        this.form.markAsUntouched();
    }
}
