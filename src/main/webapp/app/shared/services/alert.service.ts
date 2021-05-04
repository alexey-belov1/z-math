import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {AlertType, IAlert} from "../interfaces";

@Injectable({ providedIn: 'root' })
export class AlertService {
    private subject = new Subject<IAlert>();

    onAlert(): Observable<IAlert> {
        return this.subject.asObservable();
    }

    success(message: string) {
        this.alert({
            type: AlertType.Success,
            message: message
        });
    }

    error(message: string) {
        this.alert({
            type: AlertType.Error,
            message: message
        });
    }

    info(message: string) {
        this.alert({
            type: AlertType.Info,
            message: message
        });
    }

    warn(message: string) {
        this.alert({
            type: AlertType.Warning,
            message: message
        });
    }

    alert(alert: IAlert) {
        this.subject.next(alert);
    }
}
