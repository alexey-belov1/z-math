import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IReview} from "../model/review.model";
import {SERVER_API_URL} from "../constant/url.constant";

type EntityArrayResponseType = HttpResponse<IReview[]>;

@Injectable({providedIn: 'root'})
export class ReviewService {

    private resourceUrl = SERVER_API_URL + 'api/review';

    constructor(private http: HttpClient) { }

    findAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IReview[]>(this.resourceUrl, {observe: 'response'});
    }

    save(review: IReview): Observable<any> {
        return this.http.post(this.resourceUrl, review);
    }
}
