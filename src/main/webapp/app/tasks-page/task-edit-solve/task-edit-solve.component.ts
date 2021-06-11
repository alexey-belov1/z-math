import {Component, Input, OnInit} from '@angular/core';
import {EventData, ITask} from "../../shared/interfaces";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {EventBusService} from "../../shared/services/event-bus.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TaskService} from "../../shared/services/task.service";

@Component({
    selector: 'app-task-edit-solve',
    templateUrl: './task-edit-solve.component.html',
    styleUrls: ['./task-edit-solve.component.scss', '../task-edit.style.scss']
})
export class TaskEditSolveComponent implements OnInit {

    @Input() task: ITask;

    files: File[] = [];

    form: FormGroup;

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

    close(): void {
        this.activeModal.dismiss();
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

    update(): void {

        console.warn("this files", this.files);
        if (this.form.invalid) {
            return;
        }

        this.task.status.id = 4;
        this.taskService.update(this.task, this.files).subscribe(() => {
            this.close();
            this.eventBusService.emit(new EventData("taskEdit", null));
        });
    }
}
