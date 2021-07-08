import {Component, OnInit, Input, ComponentFactoryResolver, OnDestroy} from '@angular/core';
import {TaskEditCostComponent} from "../task-edit-cost/task-edit-cost.component";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {TaskEditRefuseComponent} from "../task-edit-refuse/task-edit-refuse.component";
import {TaskEditSolveComponent} from "../task-edit-solve/task-edit-solve.component";
import {Subscription} from "rxjs";
import {TaskEditPaymentComponent} from "../task-edit-payment/task-edit-payment.component";
import {AttachedFileService} from "../../../shared/services/attached-file.service";
import {TaskService} from "../../../shared/services/task.service";
import {EventBusService} from "../../../shared/services/event-bus.service";
import {AuthService} from "../../../shared/services/auth.service";
import {ITask} from "../../../shared/model/task.model";
import {EventData} from "../../../shared/model/event-data.model";
import {IAttachedFile} from "../../../shared/model/attached-file.model";

@Component({
    selector: 'app-task-detail',
    templateUrl: './task-detail.component.html',
    styleUrls: ['../task-edit.style.scss']
})
export class TaskDetailComponent implements OnInit, OnDestroy {

    @Input() task: ITask;

    onTaskEditSub: Subscription;

    constructor(
        private attachedFileService: AttachedFileService,
        private resolver: ComponentFactoryResolver,
        protected modalService: NgbModal,
        public activeModal: NgbActiveModal,
        private taskService: TaskService,
        private eventBusService: EventBusService,
        public authService: AuthService
    ) {}

    ngOnInit(): void {
        this.onTaskEditSub = this.eventBusService.on("taskEdit", () => {
            this.taskService.find(this.task.id).subscribe(res => {
                this.task = res.body;
            })
        });
    }

    download(attachedFile: IAttachedFile): void {
        this.attachedFileService.download(attachedFile.id).subscribe(res => {
            const url = window.URL.createObjectURL(res.body);
            const a = document.createElement('a');
            document.body.appendChild(a);
            a.setAttribute('style', 'display: none');
            a.href = url;
            a.download = attachedFile.name;
            a.click();
            window.URL.revokeObjectURL(url);
            a.remove();
        });
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

    showTaskEditPaymentModal(): void {
        this.showModal(TaskEditPaymentComponent);
    }

    private showModal(component: any): void {
        const modalRef = this.modalService.open(component, { backdrop: 'static' });
        modalRef.componentInstance.task = this.task;
    }

    deleteTask(): void {
        if (confirm(`Вы действительно хотите удалить заказ №${this.task.id}?`)) {
            this.taskService.delete(this.task.id).subscribe(() => {
                this.onTaskEditSub.unsubscribe();
                this.eventBusService.emit(new EventData("taskEdit", null));
                this.close();
            });
        }
    }

    isCurrentUser(): boolean {
        return this.task.userLogin === this.authService.Login;
    }

    hasAnyRoleOrCurrentUser(roles: string[]): boolean {
        return this.task.userLogin === this.authService.Login
            || this.authService.hasAnyRole(roles);
    }

    close(): void {
        this.activeModal.dismiss();
    }

    ngOnDestroy(): void {
        if (!this.onTaskEditSub.closed) {
            this.onTaskEditSub.unsubscribe();
        }
    }
}
