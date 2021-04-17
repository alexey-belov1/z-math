import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {User} from '../../interfaces';

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
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    /*this.route.queryParams.subscribe((params: Params) => {
      if (params['loginAgain']) {
        this.message = 'Пожалуйста, введите данные';
      } else if (params['authFailed']) {
        this.message = 'Сессия истекла. Введите данные заного';
      }
    });
*/
    this.form = new FormGroup({
      login: new FormControl(null, [
        Validators.required
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(3)
      ])
    });
  }

  logout(): void {
    /*event.preventDefault();*/
    this.authService.logout();
    this.router.navigate(['/']);
  }

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  submit(): void {
    if (this.form.invalid) {
      return;
    }

    this.submitted = true;

    const user: User = {
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
}
