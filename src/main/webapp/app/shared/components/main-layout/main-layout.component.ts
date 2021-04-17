import { Component, OnInit } from '@angular/core';
import {animate, style, transition, trigger} from '@angular/animations';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-main-layout',
  templateUrl: './main-layout.component.html',
  styleUrls: ['./main-layout.component.scss'],
  animations: [
    trigger('routeAnimations', [
      transition(':enter', [
        style({ opacity: 0.2 }),
        animate(350)
      ])
    ])
  ]
})
export class MainLayoutComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  prepareRoute(outlet: RouterOutlet): void {
    return outlet && outlet.activatedRouteData && outlet.activatedRouteData.animation;
  }

}
