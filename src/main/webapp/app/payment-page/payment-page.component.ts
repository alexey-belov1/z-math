import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import { ITask} from "../shared/interfaces";
import { HttpParams, HttpResponse} from "@angular/common/http";
import {TaskService} from "../shared/services/task.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-payment-page',
    templateUrl: './payment-page.component.html',
    styleUrls: ['./payment-page.component.scss']
})
export class PaymentPageComponent implements OnInit {

    tasks: ITask[] = [];

    form: FormGroup;

    constructor(
        private taskService: TaskService,
        private router: Router
    ) { }

    ngOnInit(): void {
        let options: HttpParams = new HttpParams();

        //options = options.set('size', this.itemsPerPage.toString());

        this.taskService.query(options).subscribe(
            (res: HttpResponse<ITask[]>) => {
                console.warn("this is res", res);
                this.tasks = res.body ? res.body : [];
            }
        );

        this.form = new FormGroup({
            task: new FormControl(null, [Validators.required]),
            cost: new FormControl(null)
        });
    }

    setCost() {
        const task = this.form.get(['task']).value;
        this.form.get(['cost']).patchValue(task.cost);
    }

    submit() {
        if (this.form.invalid) {
            this.form.markAllAsTouched();
            return;
        }

        const task = this.form.get(['task']).value;
        task.status.id = 3;

        this.taskService.update(task, []).subscribe(() => {
            this.router.navigate(['/tasks']);
        });
    }



}
