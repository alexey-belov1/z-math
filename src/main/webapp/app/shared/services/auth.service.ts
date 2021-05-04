import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthResponse, IUser} from '../interfaces';
import {tap} from 'rxjs/operators';

@Injectable({providedIn: 'root'})
export class AuthService {
  constructor(private http: HttpClient) {}

  get token(): string {
    const expDate = new Date(localStorage.getItem('zm-token-exp'));
    if (new Date() > expDate) {
      this.logout();
      return null;
    }
    return localStorage.getItem('zm-token');
  }

  get Login(): string {
    return localStorage.getItem('zm-login');
  }

  login(user: IUser): Observable<any> {
    return this.http.post('http://localhost:8080/login', user)
      .pipe(
        tap(this.setToken)
      );
  }

  logout(): void {
    this.setToken(null);
  }

  isAuthenticated(): boolean {
    return !!this.token;
  }

  private setToken(response: AuthResponse | null): void {
    if (response) {
      const expDate = new Date(new Date().getTime() + +response.Expires);
      localStorage.setItem('zm-token', response.Authorization);
      localStorage.setItem('zm-token-exp', expDate.toString());
      localStorage.setItem('zm-login', response.Login);
    } else {
      localStorage.clear();
    }
  }
}
