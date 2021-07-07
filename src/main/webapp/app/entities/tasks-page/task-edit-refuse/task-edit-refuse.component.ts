import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TaskService} from "../../../shared/services/task.service";
import {EventBusService} from "../../../shared/services/event-bus.service";
import {ITask} from "../../../shared/model/task.model";
import {EventData} from "../../../shared/model/event-data.model";

@Component({
  selector: 'app-task-edit-refuse',
  templateUrl: './task-edit-refuse.component.html',
  styleUrls: ['../task-edit.style.scss']
})
export class TaskEditRefuseComponent implements OnInit{

    @Input() task: ITask;

    form: FormGroup;

    submitted = false;

    constructor(
        public activeModal: NgbActiveModal,
        private taskService: TaskService,
        private eventBusService: EventBusService
    ) { }

    ngOnInit(): void {
        this.form = new FormGroup({
            cause: new FormControl(null, [Validators.required]),
        });
    }

    submit(): void {
        if (this.form.invalid) {
            return;
        }
        this.task.status.id = 5;
        this.task.cause = this.form.get(['cause']).value;

        this.submitted = true;

        this.taskService.update(this.task, []).subscribe(() => {
            this.close();
            this.eventBusService.emit(new EventData("taskEdit", null));
        }, () => {
            this.submitted = false;
        });
    }

    close(): void {
        this.activeModal.dismiss();
    }
}
