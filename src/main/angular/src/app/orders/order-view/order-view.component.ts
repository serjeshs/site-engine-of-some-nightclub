import {Component, Input, OnInit} from '@angular/core';
import {MenuOrder} from "../../dto/menuOrder";
import {MenuCategoryDto} from "../../dto/menuCategoryDto";

@Component({
  selector: 'app-order-view',
  templateUrl: './order-view.component.html',
  styleUrls: ['./order-view.component.css']
})
export class OrderViewComponent implements OnInit {

  @Input() order: MenuOrder = new MenuOrder();
  @Input() menuCategories: MenuCategoryDto[] = [];


  constructor() {
  }

  ngOnInit() {
  }

  showCategory(category: MenuCategoryDto) {
    let show = false;
    for (let c of category.categories) {
      if (this.showCategory(c)) {
        return true;
      }
    }
    for (let menuItem of category.menuItems) {
      if (menuItem.count.valueOf() > 0)
        return true;
    }
    return show;
  }

  get totalMoney() {
    let total = 0;
    for (let foodKey in this.order.food) {
      total += this.order.foodPrice[foodKey] * this.order.food[foodKey];
    }
    return total;
  }
}
