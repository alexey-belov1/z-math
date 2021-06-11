import {Component, Input} from '@angular/core';
import {EventData, ITask} from "../../shared/interfaces";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {TaskService} from "../../shared/services/task.service";
import {EventBusService} from "../../shared/services/event-bus.service";

@Component({
  selector: 'app-task-edit-refuse',
  templateUrl: './task-edit-refuse.component.html',
  styleUrls: ['./task-edit-refuse.component.scss', '../task-edit.style.scss']
})
export class TaskEditRefuseComponent {

    @Input() task: ITask;

    constructor(
        public activeModal: NgbActiveModal,
        private taskService: TaskService,
        private eventBusService: EventBusService
    ) { }

    update(): void {
        this.task.status.id = 5;
        this.taskService.update(this.task, []).subscribe(() => {
            this.close();
            this.eventBusService.emit(new EventData("taskEdit", null));
        });
    }

    close(): void {
        this.activeModal.dismiss();
    }
}
