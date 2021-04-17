import { Component, OnInit } from '@angular/core';
import {TaskService} from '../shared/services/task.service';
import {Task} from '../shared/interfaces';
import {animate, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-tasks-page',
  templateUrl: './tasks-page.component.html',
  styleUrls: ['./tasks-page.component.scss'],
  animations: [
    trigger('long-enter', [
      transition(':enter', [
        style({ opacity: 0.2 }),
        animate(350)
      ])
    ])
  ]
})
export class TasksPageComponent implements OnInit {

  tasks: Task[] = [];
  toggle: boolean[] = [];

  constructor(private taskService: TaskService) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll(): void {
    this.taskService.getAll()
      .subscribe(tasks => {
        this.tasks = tasks;
        for (const task of this.tasks) {
          this.toggle.push(true);
        }
        console.log(this.toggle);
      });
  }

  getItem(i: number): string {
    return 'item' + i;
  }

  changeToggle(i: number): void {
    this.toggle[i] = !this.toggle[i];
  }

/*  toggle(el: string): void {
    document.getElementById(el).classList.toggle('visibility');
  }*/

/*  contains(el: string): boolean {
    return document.getElementById(el).classList.contains('toggle');
  }*/
}
