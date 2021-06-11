import {Component, Input, OnInit} from '@angular/core';
import {ITask} from "../../shared/interfaces";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {EventBusService} from "../../shared/services/event-bus.service";

@Component({
  selector: 'app-task-edit-solve',
  templateUrl: './task-edit-solve.component.html',
  styleUrls: ['./task-edit-solve.component.scss', '../task-edit.style.scss']
})
export class TaskEditSolveComponent implements OnInit {

    @Input() task: ITask;

    files: File[] = [];


    constructor(
        public activeModal: NgbActiveModal,
        private eventBusService: EventBusService
    ) { }

    ngOnInit(): void {
    }

    close(): void {
        this.activeModal.dismiss();
    }

   setFile(event: Event) {
        this.files.push((<HTMLInputElement>event.target).files.item(0));
    }

    cleanFile(file: File) {
        this.files = this.files.filter(x => x !== file);
    }
}
