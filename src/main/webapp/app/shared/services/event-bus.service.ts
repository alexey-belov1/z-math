import { Injectable } from '@angular/core';
import {filter, map} from "rxjs/operators";
import {Subject, Subscription} from "rxjs";
import {EventData} from "../model/event-data.model";

@Injectable({providedIn: 'root'})
export class EventBusService {

    private subject$ = new Subject();

    constructor() { }

    on(eventName: string, action: any) : Subscription {
        return this.subject$.pipe(
            filter((event: EventData) => event.name === eventName),
            map((event: EventData) => event.value)
        ).subscribe(action);
    }

    emit(event: EventData) {
        this.subject$.next(event);
    }
}


