import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IReview} from "../model/review.model";

type EntityArrayResponseType = HttpResponse<IReview[]>;

@Injectable({providedIn: 'root'})
export class ReviewService {

  constructor(private http: HttpClient) {}

  findAll(): Observable<EntityArrayResponseType> {
    return this.http
        .get<IReview[]>('http://localhost:8080/review/', { observe: 'response'});
  }

  save(review: IReview): Observable<any> {
    return this.http.post('http://localhost:8080/review/', review);
  }
}
