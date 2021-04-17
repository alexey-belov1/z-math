import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Review} from '../interfaces';

@Injectable({providedIn: 'root'})
export class ReviewService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Review[]> {
    return this.http.get<Review[]>('http://localhost:8080/review/');
  }

  save(review: Review): Observable<any> {
    return this.http.post('http://localhost:8080/review/', review);
  }
}
