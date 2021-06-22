import {Component, Input, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MethodService} from "../../../shared/services/method.service";
import {TaskService} from "../../../shared/services/task.service";
import {EventBusService} from "../../../shared/services/event-bus.service";
import {ITask} from "../../../shared/model/task.model";
import {IMethod, Method} from "../../../shared/model/method.model";
import {EventData} from "../../../shared/model/event-data.model";

@Component({
    selector: 'app-task-edit-payment',
    templateUrl: './task-edit-payment.component.html',
    styleUrls: ['./task-edit-payment.component.scss', '../task-edit.style.scss']
})
export class TaskEditPaymentComponent implements OnInit {

    @Input() task: ITask;

    form: FormGroup;

    methods: IMethod[] = [];

    constructor(
        public activeModal: NgbActiveModal,
        private methodService: MethodService,
        private taskService: TaskService,
        private eventBusService: EventBusService
    ) { }


    ngOnInit(): void {
        this.getAll();

        this.form = new FormGroup({
            methodId: new FormControl(null, [Validators.required]),
            paid: new FormControl(null, [Validators.required])
        });

        this.form.get(['paid']).patchValue(this.task.cost);
    }

    getAll(): void {
        this.methodService.getAll()
            .subscribe(methods => {
                this.methods = methods;
                console.log(methods);
            });
    }

    close(): void {
        this.activeModal.dismiss();
    }

    update(): void {
        if (this.form.invalid) {
            return;
        }
        this.task.status.id = 3;
        this.task.method = new Method(this.form.get(['methodId']).value);
        this.task.paid = this.form.get(['paid']).value;
        console.warn('this task', this.task);
        this.taskService.update(this.task, []).subscribe(() => {
            this.close();
            this.eventBusService.emit(new EventData("taskEdit", null));
        });
    }
}
