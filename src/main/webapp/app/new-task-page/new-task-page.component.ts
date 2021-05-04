import {Component, OnInit} from '@angular/core';
import {SubjectService} from '../shared/services/subject.service';
import {ISubject, ITask} from '../shared/interfaces';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {TaskService} from '../shared/services/task.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-new-task-page',
    templateUrl: './new-task-page.component.html',
    styleUrls: ['./new-task-page.component.scss']
})
export class NewTaskPageComponent implements OnInit {

    task: ITask = null;

    times = Array.from(Array(24).keys());
    subjects: ISubject[] = [];
    form: FormGroup;
    submitted = false;

    constructor(
        private subjectService: SubjectService,
        private taskService: TaskService,
        private router: Router) {
    }

    ngOnInit(): void {
        this.getAll();

        this.form = new FormGroup({
            subject: new FormControl(null, [
                Validators.required
            ]),
            date: new FormControl(null, [
                Validators.required
            ]),
            time: new FormControl(null, [
                Validators.required
            ]),
            cost: new FormControl(null, [
                Validators.required
            ]),
            file: new FormControl(null, [
                Validators.required
            ]),
            comment: new FormControl(null, [
                Validators.required
            ]),
            contact: new FormControl(null, [
                Validators.required
            ])
        });
    }

    getAll(): void {
        this.subjectService.getAll()
            .subscribe(subjects => {
                this.subjects = subjects;
            });
    }

    setVisibility(el: string): void {
        document.getElementById(el).classList.add('visibility');
    }

    setHidden(el: string): void {
        document.getElementById(el).classList.remove('visibility');
    }

    submit(): void {
        if (this.form.invalid) {
            return;
        }

        this.submitted = true;

        const task: ITask = {
            user: {
                id: 15
            },
            subject: {
                id: this.form.value.subject
            },
            file: this.form.value.file,
            comment: this.form.value.comment,
            deadline: this.form.value.date, /* + this.form.value.time*/
            cost: this.form.value.cost,
            contact: this.form.value.contact,
            cause: this.form.value.case,
            hidden: false
        };

        const temp = {
            user: {
                id: 1
            },
            subject: {
                id: 1
            },
            file: this.form.value.file,
            comment: this.form.value.comment,
            deadline: this.form.value.date, /* + this.form.value.time*/
            cost: this.form.value.cost,
            contact: this.form.value.contact,
            cause: this.form.value.case,
            hidden: false
        };

        console.log(task);
        console.log('this temp', temp);
        this.taskService.save(temp).subscribe(() => {
            this.router.navigate(['/tasks']);
        }, () => {
            this.submitted = false;
        });
    }
}
