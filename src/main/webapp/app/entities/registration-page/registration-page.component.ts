import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {RegService} from "../../shared/services/reg.service";
import {matchValues} from "../../shared/validators/must-match.validator";
import {IUser} from "../../shared/model/user.model";

@Component({
    selector: 'app-registration-page',
    templateUrl: './registration-page.component.html',
    styleUrls: ['./registration-page.component.scss']
})
export class RegistrationPageComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    constructor(
        private regService: RegService,
        private router: Router) {
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            login: new FormControl(null, [
                Validators.required,
                Validators.minLength(6)
            ]),
            password: new FormControl(null, [
                Validators.required,
                Validators.minLength(8)
            ]),
            confirmPassword: new FormControl(null, [
                Validators.required,
                matchValues('password')
            ]),
            email: new FormControl(null, [
                Validators.required,
                Validators.email
            ]),
        });
    }


    submit(): void {

        if (this.form.invalid) {
            this.form.markAllAsTouched();
            return;
        }

        this.submitted = true;

        const user: IUser = {
            login: this.form.value.login,
            password: this.form.value.password,
            email: this.form.value.email
        };

        console.log(user);

        this.regService.signUp(user).subscribe(() => {
            this.router.navigate(['/']);
        }, () => {
            this.submitted = false;
        });
    }
}
