import {Component, Input, OnInit} from '@angular/core';
import {ITask} from '../../shared/interfaces';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.scss']
})

export class InfoComponent implements OnInit {

  @Input() task: ITask;

  constructor() { }

  ngOnInit(): void {
  }

}
