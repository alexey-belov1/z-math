import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import {AlertService} from "../../shared/services/alert.service";
import {IAlert} from "../../shared/model/alert.model";
import {AlertType} from "../../shared/model/enum/alert-type.model";


@Component({
    selector: 'app-alert-error',
    templateUrl: 'alert-error.component.html',
    styleUrls: ['./alert-error.component.scss']
})
export class AlertErrorComponent implements OnInit, OnDestroy {

    alerts: IAlert[] = [];
    alertSubscription: Subscription;

    constructor(private router: Router, private alertService: AlertService) { }

    ngOnInit() {
        this.alertSubscription = this.alertService.onAlert()
            .subscribe(alert => {
                console.warn("Alert", alert);
                if (this.alerts.map(a => a.message).indexOf(alert.message) === -1) {
                    this.alerts.push(alert);
                    setTimeout(() => this.removeAlert(alert), 3000);
                }
            });

    }

    ngOnDestroy() {
        this.alertSubscription.unsubscribe();
    }

    removeAlert(alert: IAlert) {
        this.alerts = this.alerts.filter(x => x !== alert);
    }

    cssClass(alert: IAlert): string {
        const classes = ['alert'];
        const alertTypeClass = {
            [AlertType.Success]: 'alert-success',
            [AlertType.Error]: 'alert-danger',
            [AlertType.Info]: 'alert-info',
            [AlertType.Warning]: 'alert alert-warning'
        }
        classes.push(alertTypeClass[alert.type]);
        return classes.join(' ');
    }
}
