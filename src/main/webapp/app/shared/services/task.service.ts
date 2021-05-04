import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ITask} from '../interfaces';

@Injectable({providedIn: 'root'})
export class TaskService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<ITask[]> {
    return this.http.get<ITask[]>('http://localhost:8080/task/');
  }

  save(task: any): Observable<any> {
    return this.http.post('http://localhost:8080/task/', task);
  }
}
