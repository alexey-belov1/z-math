import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IMethod} from '../interfaces';

@Injectable({providedIn: 'root'})
export class MethodService {
    constructor(private http: HttpClient) {}

    getAll(): Observable<IMethod[]> {
        return this.http.get<IMethod[]>('http://localhost:8080/method/');
    }
}
