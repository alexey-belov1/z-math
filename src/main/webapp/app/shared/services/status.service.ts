import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IStatus} from "../model/status.model";

@Injectable({providedIn: 'root'})
export class StatusService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<IStatus[]> {
    return this.http.get<IStatus[]>('http://localhost:8080/status/');
  }
}
