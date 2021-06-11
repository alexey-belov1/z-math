import {Component, Input} from '@angular/core';
import {EventData, ITask} from "../../shared/interfaces";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {TaskService} from "../../shared/services/task.service";
import {EventBusService} from "../../shared/services/event-bus.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'app-task-edit-cost',
    templateUrl: './task-edit-cost.component.html',
    styleUrls: ['./task-edit-cost.component.scss', '../task-edit.style.scss']
})
export class TaskEditCostComponent {

    @Input() task: ITask;

    form: FormGroup;

    constructor(
        public activeModal: NgbActiveModal,
        private taskService: TaskService,
        private eventBusService: EventBusService
    ) { }

    ngOnInit(): void {
        this.form = new FormGroup({
            cost: new FormControl(null, [Validators.required]),
        });
    }

    close(): void {
        this.activeModal.dismiss();
    }

    update(): void {
        if (this.form.invalid) {
            return;
        }
        this.task.status.id = 2;
        this.task.cost = this.form.get(['cost']).value;
        this.taskService.update(this.task, []).subscribe(() => {
            this.close();
            this.eventBusService.emit(new EventData("taskEdit", null));
        });
    }
}
