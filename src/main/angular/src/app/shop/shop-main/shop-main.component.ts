import {Component, OnInit} from '@angular/core';
import {ShopItem} from "../dto/shopItem";

@Component({
  selector: 'app-shop-main',
  templateUrl: './shop-main.component.html',
  styleUrls: ['./shop-main.component.css']
})
export class ShopMainComponent implements OnInit {

  items: ShopItem[];

  constructor() {
  }

  ngOnInit() {
    this.items = [
      {
        id: 39,
        name: 'Майка номер 39',
        description: 'Описание самой лучшей майки 39 39 39',
        cost: 39,
        image: 'http://qa.republic-club.by/files/2018/shop/photo5413786013408536839.jpg',
        count: 0
      },
      {
        id: 40,
        name: 'Майка номер 40',
        description: 'Описание самой лучшей майки 40 40 40',
        cost: 40,
        image: 'http://qa.republic-club.by/files/2018/shop/photo5413786013408536840.jpg',
        count: 0
      },
      {
        id: 41,
        name: 'Майка номер 41',
        description: 'Описание самой лучшей майки 41 41 41',
        cost: 41,
        image: 'http://qa.republic-club.by/files/2018/shop/photo5413786013408536841.jpg',
        count: 0
      },
      {
        id: 42,
        name: 'Майка номер 42',
        description: 'Описание самой лучшей майки 42 42 42',
        cost: 42,
        image: 'http://qa.republic-club.by/files/2018/shop/photo5413786013408536842.jpg',
        count: 0
      },
      {
        id: 43,
        name: 'Майка номер 43',
        description: 'Описание самой лучшей майки 43 43 43',
        cost: 43,
        image: 'http://qa.republic-club.by/files/2018/shop/photo5413786013408536843.jpg',
        count: 0
      },
      {
        id: 27,
        name: 'Майка номер 27',
        description: 'Описание самой лучшей майки 27 27 27',
        cost: 27,
        image: 'http://qa.republic-club.by/files/2018/shop/photo5413867519002913427.jpg',
        count: 0
      },
    ];
  }

  buy(id: number) {
    alert("Открытие окна оплаты для покупки!")
  }
}
