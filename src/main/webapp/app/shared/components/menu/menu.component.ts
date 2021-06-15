import { Component, OnInit } from '@angular/core';

export interface Item {
  router: string;
  src: string;
  text: string;
}

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuComponent implements OnInit {

  items: Item[] = [
    {
      router: '/',
      src: 'main/webapp/content/image/icon/home.png',
      text: 'Главная'
    },
    {
      router: '/tasks',
      src: 'main/webapp/content/image/icon/tasks.png',
      text: 'Список задач'
    },
    {
      router: '/rules',
      src: 'main/webapp/content/image/icon/rules.png',
      text: 'Правила <br> оформления <br>заказов'
    },
    {
      router: '/new-task',
      src: 'main/webapp/content/image/icon/new-task.png',
      text: 'Оформить заказ'
    },
/*    {
      router: '/payment',
      src: 'main/webapp/content/image/icon/payment.png',
      text: 'Оплатить заказ'
    },*/
    {
      router: '/reviews',
      src: 'main/webapp/content/image/icon/reviews.png',
      text: 'Отзывы'
    },
    {
      router: '/contacts',
      src: 'main/webapp/content/image/icon/contacts.png',
      text: 'Контакты'
    },
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
