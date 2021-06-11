import {Component, OnInit, Input, ComponentFactoryResolver, OnDestroy} from '@angular/core';
import {EventData, ITask} from "../../shared/interfaces";
import {AttachedFileService} from "../../shared/services/attached-file.service";
import {TaskEditCostComponent} from "../task-edit-cost/task-edit-cost.component";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {TaskEditRefuseComponent} from "../task-edit-refuse/task-edit-refuse.component";
import {TaskEditSolveComponent} from "../task-edit-solve/task-edit-solve.component";
import {TaskService} from "../../shared/services/task.service";
import {Subscription} from "rxjs";
import {EventBusService} from "../../shared/services/event-bus.service";

@Component({
    selector: 'app-task-detail',
    templateUrl: './task-detail.component.html',
    styleUrls: ['./task-detail.component.scss']
})
export class TaskDetailComponent implements OnInit, OnDestroy {

    @Input() task: ITask;

    // Подписка на прием сообщений от сервера по веб сокету
    onTaskEditSub: Subscription;

    constructor(
        private attachedFileService: AttachedFileService,
        private resolver: ComponentFactoryResolver,
        protected modalService: NgbModal,
        public activeModal: NgbActiveModal,
        private taskService: TaskService,
        private eventBusService: EventBusService
    ) {}

    ngOnInit(): void {
        this.onTaskEditSub = this.eventBusService.on("taskEdit", () => {
            this.taskService.find(this.task.id).subscribe((res) => {
                this.task = res.body;
            })
        });
    }

    close(): void {
        this.activeModal.dismiss();
    }

    download(attachedFileId: number, attachedFileName: string): void {
        this.attachedFileService.download(attachedFileId).subscribe(res => {
            const url = window.URL.createObjectURL(res.body);
            const a = document.createElement('a');
            document.body.appendChild(a);
            a.setAttribute('style', 'display: none');
            a.href = url;
            a.download = attachedFileName;
            a.click();
            window.URL.revokeObjectURL(url);
            a.remove();
        });
    }

    private showModal(component: any): void {
        const modalRef = this.modalService.open(component, { backdrop: 'static' });
        modalRef.componentInstance.task = this.task;
    }

    showTaskEditCostModal(): void {
        this.showModal(TaskEditCostComponent);
    }

    showTaskEditRefuseModal(): void {
        this.showModal(TaskEditRefuseComponent);
    }

    showTaskEditSolveModal(): void {
        this.showModal(TaskEditSolveComponent);
    }

    deleteTask(): void {
        this.taskService.delete(this.task.id).subscribe(() => {
            this.close();
            this.eventBusService.emit(new EventData("taskEdit", null));
        });
    }

    ngOnDestroy(): void {
        this.onTaskEditSub.unsubscribe();
    }
}
