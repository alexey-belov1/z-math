import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IUser} from '../interfaces';

@Injectable({providedIn: 'root'})
export class RegService {
  constructor(private http: HttpClient) {}

  signUp(user: IUser): Observable<any> {
    return this.http.post('http://localhost:8080/user/sign-up', user);
  }
}
