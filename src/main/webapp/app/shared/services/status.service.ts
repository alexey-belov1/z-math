import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IStatus} from "../model/status.model";
import {SERVER_API_URL} from "../constant/url.constant";

type EntityArrayResponseType = HttpResponse<IStatus[]>;

@Injectable({providedIn: 'root'})
export class StatusService {

    private resourceUrl = SERVER_API_URL + 'api/status';

    constructor(private http: HttpClient) { }

    findAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IStatus[]>(this.resourceUrl, {observe: 'response'});
    }
}
