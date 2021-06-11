import {Component, Input} from '@angular/core';
import {EventData, ITask} from "../../shared/interfaces";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {TaskService} from "../../shared/services/task.service";
import {EventBusService} from "../../shared/services/event-bus.service";

@Component({
    selector: 'app-task-edit-cost',
    templateUrl: './task-edit-cost.component.html',
    styleUrls: ['./task-edit-cost.component.scss', '../task-edit.style.scss']
})
export class TaskEditCostComponent {

    @Input() task: ITask;

    constructor(
        public activeModal: NgbActiveModal,
        private taskService: TaskService,
        private eventBusService: EventBusService
    ) { }

    close(): void {
        this.activeModal.dismiss();
    }

    update(): void {
        this.task.status.id = 2;
        this.taskService.update(this.task, []).subscribe(() => {
            this.close();
            this.eventBusService.emit(new EventData("taskEdit", null));
        });
    }
}
