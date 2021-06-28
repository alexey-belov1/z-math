import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IUser} from "../model/user.model";
import {SERVER_API_URL} from "../constant/url.constant";

@Injectable({providedIn: 'root'})
export class RegService {

    private resourceUrl = SERVER_API_URL + 'api/user';

    constructor(private http: HttpClient) { }

    signUp(user: IUser): Observable<any> {
        return this.http.post(`${this.resourceUrl}/sign-up`, user);
    }
}
