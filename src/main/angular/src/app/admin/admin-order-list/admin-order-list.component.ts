import {Component, OnInit} from '@angular/core';
import {MenuService} from "../../menu.service";
import * as moment from "moment";
import {MenuCategoryDto} from "../../dto/menuCategoryDto";
import {Event} from "../../dto/event";
import {MenuOrder} from "../../dto/menuOrder";

export class AdminOrderListWrapper {
  eventNumber: number;
  orderNumber: number;
}

@Component({
  selector: 'app-admin-order-list',
  templateUrl: './admin-order-list.component.html',
  styleUrls: ['./admin-order-list.component.css']
})
export class AdminOrderListComponent implements OnInit {
  private wrapper: AdminOrderListWrapper;
  private menuCategories: MenuCategoryDto[];
  private events: Event[];
  private model: MenuOrder;
  private orders: MenuOrder[];

  constructor(private menuService: MenuService) {
  }

  ngOnInit() {
    this.wrapper = new AdminOrderListWrapper();
    this.wrapper.eventNumber = 0;
    this.wrapper.orderNumber = 0;
    this.model = new MenuOrder();
    this.model.food = {};
    this.menuService.getMenuPageSummary().subscribe(menuPage => {
      this.menuCategories = menuPage.categories;
      this.menuCategories.forEach(mainCategory => {
        mainCategory.categories.forEach(category => {
          category.menuItems.forEach(item => {
            item.count = 0;
          })
        })
      });
      this.events = menuPage.events;
      this.events.forEach(event => {
        event.startEvent = moment(event.startEvent).format('D MMMM');
      })
    });
  }

  get eventChoice() {
    console.log("eventChoice", this.wrapper.eventNumber);
    return this.wrapper.eventNumber > 0;
  }

  get orderChoice() {
    console.log("orderChoice", this.wrapper.orderNumber);
    return this.wrapper.orderNumber > 0;
  }

  changeEvent() {
    this.menuService.getOrders(this.wrapper.eventNumber).subscribe(responseArray => {
      if (Array.isArray(responseArray)) {
        this.orders = responseArray;
      }
    });
  }

  onOrderClick(order) {
    this.wrapper.orderNumber = order.id;
    this.menuService.getOrder(this.wrapper.orderNumber).subscribe(order => {
      this.model = order;
      console.log(this.model.food);
    });
  }
}
