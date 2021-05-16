import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AttachedFileService {
    private resourceUrl = 'http://localhost:8080/attached-files';

    constructor(protected http: HttpClient) {}

    download(id: number): Observable<HttpResponse<Blob>> {
        return this.http.get(`${this.resourceUrl}/download/${id}`, { observe: 'response', responseType: 'blob' });
    }

    delete(id: number): Observable<HttpResponse<{}>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
