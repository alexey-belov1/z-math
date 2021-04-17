import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Subject} from '../interfaces';

@Injectable({providedIn: 'root'})
export class SubjectService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Subject[]> {
    return this.http.get<Subject[]>('http://localhost:8080/subject/');
  }
}
