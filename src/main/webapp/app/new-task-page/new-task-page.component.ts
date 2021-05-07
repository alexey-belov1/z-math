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
            subject: new FormControl(null, [Validators.required]),
            deadline: new FormControl(null, [Validators.required]),
            cost: new FormControl(null, [Validators.required]),
            file: new FormControl(null, [Validators.required]),
            comment: new FormControl(null),
            contact: new FormControl(null)
        });
    }

    getAll(): void {
        this.subjectService.getAll()
            .subscribe(subjects => {
                this.subjects = subjects;
            });
    }

    submit(): void {

        if (this.form.invalid) {
            this.form.markAllAsTouched();
            return;
        }

        const task: ITask = {
            subject: this.form.value.subject,
            file: this.form.value.file,
            comment: this.form.value.comment,
            deadline: this.form.value.deadline,
            cost: this.form.value.cost,
            contact: this.form.value.contact,
            hidden: false
        };
        console.warn("Task", task);

        this.submitted = true;

        this.taskService.save(task).subscribe(() => {
            this.router.navigate(['/tasks']);
        }, () => {
            this.submitted = false;
        });
    }
}
