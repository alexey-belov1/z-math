import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TaskService} from "../../../shared/services/task.service";
import {EventBusService} from "../../../shared/services/event-bus.service";
import {ITask} from "../../../shared/model/task.model";
import {EventData} from "../../../shared/model/event-data.model";

@Component({
    selector: 'app-task-edit-solve',
    templateUrl: './task-edit-solve.component.html',
    styleUrls: ['../task-edit.style.scss']
})
export class TaskEditSolveComponent implements OnInit {

    @Input() task: ITask;

    files: File[] = [];

    form: FormGroup;

    submitted = false;

    constructor(
        public activeModal: NgbActiveModal,
        private taskService: TaskService,
        private eventBusService: EventBusService
    ) {
    }

    ngOnInit(): void {
        this.form = new FormGroup({
            files: new FormControl(null, [Validators.required])
        });
    }

    setFile(event: Event) {
        this.files.push((<HTMLInputElement>event.target).files.item(0));
        this.updateValidatorRequiredFiles();
    }

    cleanFile(file: File) {
        this.files = this.files.filter(x => x !== file);
        this.updateValidatorRequiredFiles();
    }

    updateValidatorRequiredFiles(): void {
        const formControl = this.form.get('files');
        if (this.files.length === 0) {
            formControl.setValidators(Validators.required);
        } else {
            formControl.clearValidators();
        }
        formControl.updateValueAndValidity();
    }

    submit(): void {
        if (this.form.invalid) {
            return;
        }

        this.task.status.id = 4;

        this.submitted = false;

        this.taskService.update(this.task, this.files).subscribe(() => {
            this.close();
            this.eventBusService.emit(new EventData("taskEdit", null));
        }, () => {
            this.submitted = true;
        });
    }


    close(): void {
        this.activeModal.dismiss();
    }
}
