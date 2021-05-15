import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ITask} from '../interfaces';

type EntityResponseType = HttpResponse<ITask>;
type EntityArrayResponseType = HttpResponse<ITask[]>;

@Injectable({providedIn: 'root'})
export class TaskService {

    private resourceUrl = 'http://localhost:8080/task/';

    constructor(private http: HttpClient) {
    }

    getAll(): Observable<ITask[]> {
        return this.http.get<ITask[]>('http://localhost:8080/task/');
    }

    query(req?: HttpParams): Observable<EntityArrayResponseType> {
        return this.http
            .get<ITask[]>(this.resourceUrl, {params: req, observe: 'response'});
    }

    save(task: any): Observable<any> {
        return this.http.post('http://localhost:8080/task/', task);
    }
}
