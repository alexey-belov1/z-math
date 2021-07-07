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
    styleUrls: ['../task-edit.style.scss']
})
export class TaskEditPaymentComponent implements OnInit {

    @Input() task: ITask;

    methods: IMethod[] = [];

    form: FormGroup;

    submitted = false;

    constructor(
        public activeModal: NgbActiveModal,
        private methodService: MethodService,
        private taskService: TaskService,
        private eventBusService: EventBusService
    ) { }


    ngOnInit(): void {
        this.methodService.findAll().subscribe(res => {
            this.methods = res.body;
        });

        this.form = new FormGroup({
            methodId: new FormControl(null, [Validators.required]),
            amount: new FormControl(this.task.cost, [Validators.required])
        });
    }

    submit(): void {
        if (this.form.invalid) {
            return;
        }

        this.task.status.id = 3;
        this.task.payment = {
            method: new Method(this.form.get(['methodId']).value),
            amount: this.form.get(['amount']).value
        };

        this.submitted = true;

        this.taskService.setPayment(this.task).subscribe(() => {
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
