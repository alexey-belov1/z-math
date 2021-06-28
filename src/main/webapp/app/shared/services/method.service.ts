import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IMethod} from "../model/method.model";
import {SERVER_API_URL} from "../constant/url.constant";

type EntityArrayResponseType = HttpResponse<IMethod[]>;

@Injectable({providedIn: 'root'})
export class MethodService {

    private resourceUrl = SERVER_API_URL + 'api/method';

    constructor(private http: HttpClient) {}

    findAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IMethod[]>(this.resourceUrl, { observe: 'response' });
    }
}
