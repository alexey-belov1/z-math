import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthService} from "../../shared/services/auth.service";
import {IUser} from "../../shared/model/user.model";

@Component({
    selector: 'app-login-panel',
    templateUrl: './login-panel.component.html',
    styleUrls: ['./login-panel.component.scss']
})
export class LoginPanelComponent implements OnInit {

    form: FormGroup;

    submitted = false;

    constructor(
        public authService: AuthService,
        private router: Router
    ) {
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            login: new FormControl(null, [Validators.required]),
            password: new FormControl(null, [Validators.required])
        });
    }

    submit(): void {
        if (this.form.invalid) {
            this.form.markAllAsTouched();
            return;
        }

        const user: IUser = {
            login: this.form.get('login').value,
            password: this.form.get('password').value
        };

        this.submitted = true;

        this.authService.login(user).subscribe(() => {
            this.router.navigate(['/']).then();
        }, () => {
            this.submitted = false;
        });
    }

    markAllAsUntouched(): void {
        this.form.markAsUntouched();
    }

    logout(): void {
        this.authService.logout();
        this.router.navigate(['/']).then();
    }

    isAuthenticated(): boolean {
        return this.authService.isAuthenticated();
    }
}
