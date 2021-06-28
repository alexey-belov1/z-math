import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ISubject} from "../model/subject.model";
import {SERVER_API_URL} from "../constant/url.constant";

type EntityArrayResponseType = HttpResponse<ISubject[]>;

@Injectable({providedIn: 'root'})
export class SubjectService {

    private resourceUrl = SERVER_API_URL + 'api/subject';

    constructor(private http: HttpClient) { }

    findAll(): Observable<EntityArrayResponseType> {
        return this.http
            .get<ISubject[]>(this.resourceUrl, {observe: 'response'});
    }
}
