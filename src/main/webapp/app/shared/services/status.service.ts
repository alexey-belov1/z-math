import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IStatus} from "../model/status.model";

type EntityArrayResponseType = HttpResponse<IStatus[]>;

@Injectable({providedIn: 'root'})
export class StatusService {

  constructor(private http: HttpClient) {}

  findAll(): Observable<EntityArrayResponseType> {
    return this.http.get<IStatus[]>('http://localhost:8080/status/', { observe: 'response'});
  }
}
