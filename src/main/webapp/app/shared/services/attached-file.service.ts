import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import {SERVER_API_URL} from "../constant/url.constant";

@Injectable({ providedIn: 'root' })
export class AttachedFileService {

    private resourceUrl = SERVER_API_URL + 'api/attached-files';

    constructor(protected http: HttpClient) {}

    download(id: number): Observable<HttpResponse<Blob>> {
        return this.http.get(`${this.resourceUrl}/download/${id}`, { observe: 'response', responseType: 'blob' });
    }

    delete(id: number): Observable<HttpResponse<{}>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
