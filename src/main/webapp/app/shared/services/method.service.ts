import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IMethod} from "../model/method.model";

type EntityArrayResponseType = HttpResponse<IMethod[]>;

@Injectable({providedIn: 'root'})
export class MethodService {

    constructor(private http: HttpClient) {}

    findAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IMethod[]>('http://localhost:8080/method/', { observe: 'response' });
    }
}
