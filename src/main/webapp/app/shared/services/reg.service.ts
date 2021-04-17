import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from '../interfaces';

@Injectable({providedIn: 'root'})
export class RegService {
  constructor(private http: HttpClient) {}

  signUp(user: User): Observable<any> {
    return this.http.post('http://localhost:8080/user/sign-up', user);
  }
}
