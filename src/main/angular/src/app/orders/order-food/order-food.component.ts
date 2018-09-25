import {Component, Input, OnInit} from '@angular/core';
import {MenuCategoryDto} from "../../dto/menuCategoryDto";
import {MenuItemPriceDto} from "../../dto/menuItemPriceDto";
import {MenuOrder} from "../../dto/menuOrder";

@Component({
  selector: 'app-order-food',
  templateUrl: './order-food.component.html',
  styleUrls: ['./order-food.component.css']
})
export class OrderFoodComponent implements OnInit {
  @Input() menuCategories: MenuCategoryDto[];
  @Input() order: MenuOrder;
  constructor() { }

  ngOnInit() {
  }

  increment(item: MenuItemPriceDto) {
    item.count = item.count + 1;
    this.storeCountInModel(item);
  }

  decrement(item: MenuItemPriceDto) {
    item.count = item.count - 1;
    if (item.count < 0) {
      item.count = 0;
    }
    this.storeCountInModel(item);
  }

  private storeCountInModel(item: MenuItemPriceDto) {
    this.order.food[item.itemPriceId] = item.count;
    this.order.foodPrice[item.itemPriceId] = item.price;
  }

  get totalMoney() {
    let total = 0;
    for (let foodKey in this.order.food) {
      total += this.order.foodPrice[foodKey] * this.order.food[foodKey];
    }
    this.order.totalMoney = total;
    return total;
  }
}
