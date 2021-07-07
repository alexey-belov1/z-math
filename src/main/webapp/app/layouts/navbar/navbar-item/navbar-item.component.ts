import {Component, Input, OnInit} from '@angular/core';
import {Item} from '../navbar.component';
import {animate, state, style, transition, trigger} from '@angular/animations';

@Component({
  selector: 'app-navbar-item',
  templateUrl: './navbar-item.component.html',
  styleUrls: ['./navbar-item.component.scss'],
  animations: [
    trigger('hover', [
      state('false', style({background: 'none'})),
      state('true', style({background: '#5a5a5a'})),
      transition('false => true', [ animate(150)]),
      transition('true => false', [ animate(150)])
    ])
  ]
})
export class NavbarItemComponent implements OnInit {

  hover = false;

  @Input() item: Item;

  constructor() { }

  ngOnInit(): void {
  }

  setHoverTrue(): void {
      this.hover = true;
  }

  setHoverFalse(): void {
      this.hover = false;
  }
}
