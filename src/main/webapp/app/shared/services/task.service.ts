import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ITask} from "../model/task.model";

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

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITask>(`${this.resourceUrl}${id}`, { observe: 'response' });
    }

    query(req?: HttpParams): Observable<EntityArrayResponseType> {
        return this.http
            .get<ITask[]>(this.resourceUrl, {params: req, observe: 'response'});
    }

/*    save(task: any): Observable<any> {
        return this.http.post('http://localhost:8080/task/', task);
    }*/

    save(task: any, files: File[]): Observable<EntityResponseType> {
        const formData: FormData = new FormData();
        formData.append(
            'task',
            new Blob([JSON.stringify(task)], {
                type: 'application/json'
            })
        );
        for (let i = 0; i < files.length; i++) {
            formData.append('files', files[i]);
        }

        return this.http
            .post<ITask>(this.resourceUrl, formData, { observe: 'response' });
    }

    update(task: any, files: File[]): Observable<EntityResponseType> {
        const formData: FormData = new FormData();
        formData.append(
            'task',
            new Blob([JSON.stringify(task)], {
                type: 'application/json'
            })
        );
        for (let i = 0; i < files.length; i++) {
            formData.append('files', files[i]);
        }
        return this.http
            .put<ITask>(this.resourceUrl, formData, { observe: 'response' });
    }

    delete(id: number): Observable<EntityResponseType> {
        return this.http.delete(`${this.resourceUrl}${id}`, { observe: 'response' });
    }

}
