import {Component, Input, OnInit} from '@angular/core';
import {Item} from '../menu.component';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-item-menu',
  templateUrl: './item-menu.component.html',
  styleUrls: ['./item-menu.component.scss'],
  animations: [
    trigger('hover', [
      state('false', style({background: 'none'})),
      state('true', style({background: '#5a5a5a'})),
      transition('false => true', [ animate(150)]),
      transition('true => false', [ animate(150)])
    ])
  ]
})
export class ItemMenuComponent implements OnInit {

  hover = false;

  @Input() item: Item;

  constructor() { }

  ngOnInit(): void {
  }

  log(l: boolean): void {
    console.log(l);
  }
}
