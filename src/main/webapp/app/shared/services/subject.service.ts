import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ISubject} from "../model/subject.model";

type EntityArrayResponseType = HttpResponse<ISubject[]>;

@Injectable({providedIn: 'root'})
export class SubjectService {

  constructor(private http: HttpClient) {}

  findAll(): Observable<EntityArrayResponseType> {
    return this.http
        .get<ISubject[]>('http://localhost:8080/subject/', { observe: 'response'});
  }
}
