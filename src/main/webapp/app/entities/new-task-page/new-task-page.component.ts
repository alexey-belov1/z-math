import {Component, OnInit} from '@angular/core';
import { FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {SubjectService} from "../../shared/services/subject.service";
import {TaskService} from "../../shared/services/task.service";
import {ITask} from "../../shared/model/task.model";
import {ISubject} from "../../shared/model/subject.model";
import {DatePipe} from "@angular/common";

@Component({
    selector: 'app-new-task-page',
    templateUrl: './new-task-page.component.html',
    styleUrls: ['./new-task-page.component.scss'],
    providers: [DatePipe]
})
export class NewTaskPageComponent implements OnInit {

    files: File[] = [];

    subjects: ISubject[] = [];

    minDate: string;

    form: FormGroup;

    submitted = false;

    constructor(
        private subjectService: SubjectService,
        private taskService: TaskService,
        private router: Router,
        private datePipe: DatePipe) {
    }

    ngOnInit(): void {
        this.subjectService.findAll().subscribe(res => {
            this.subjects = res.body;
        });

        this.form = new FormGroup({
            subject: new FormControl(null, [Validators.required]),
            deadline: new FormControl(null, [Validators.required]),
            preparedCost: new FormControl(null, [Validators.required, Validators.pattern("^[0-9]*$")]),
            comment: new FormControl(null),
            contact: new FormControl(null),
            files: new FormControl(null, [Validators.required])
        });

        this.minDate = this.minDateToString();
    }

    submit(): void {
        if (this.form.invalid) {
            this.form.markAllAsTouched();
            return;
        }
        const task: ITask = {
            subject: this.form.get('subject').value,
            deadline: this.form.get('deadline').value,
            preparedCost: this.form.get('preparedCost').value,
            comment: this.form.get('comment').value,
            contact: this.form.get('contact').value,
            archived: false
        };
        this.submitted = true;

        this.taskService.save(task, this.files).subscribe(() => {
            this.router.navigate(['/tasks']).then();
        }, () => {
            this.submitted = false;
        });
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

    private minDateToString(): string {
        const today = new Date()
        const tomorrow = new Date(today)
        tomorrow.setDate(tomorrow.getDate() + 1)
        return this.datePipe.transform(tomorrow, "yyyy-MM-ddThh:mm");
    }
}
