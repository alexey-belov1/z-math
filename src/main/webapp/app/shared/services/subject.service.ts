import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ISubject} from "../model/subject.model";

@Injectable({providedIn: 'root'})
export class SubjectService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<ISubject[]> {
    return this.http.get<ISubject[]>('http://localhost:8080/subject/');
  }
}
