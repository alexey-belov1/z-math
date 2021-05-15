import {EventEmitter, Component, OnInit, Output, Input} from '@angular/core';
import {ITask} from "../../shared/interfaces";

@Component({
    selector: 'app-task-detail',
    templateUrl: './task-detail.component.html',
    styleUrls: ['./task-detail.component.scss']
})
export class TaskDetailComponent implements OnInit {

    @Input() task: ITask;
    @Output() close = new EventEmitter<void>()

    constructor() {
    }

    ngOnInit(): void {
    }
}
