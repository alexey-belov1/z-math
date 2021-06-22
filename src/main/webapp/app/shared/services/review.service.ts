import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IReview} from "../model/review.model";

@Injectable({providedIn: 'root'})
export class ReviewService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<IReview[]> {
    return this.http.get<IReview[]>('http://localhost:8080/review/');
  }

  save(review: IReview): Observable<any> {
    return this.http.post('http://localhost:8080/review/', review);
  }
}
