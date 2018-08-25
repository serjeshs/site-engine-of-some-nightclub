import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Table} from "../../dto/table";
import {MenuCategoryDto} from "../../dto/menuCategoryDto";
import {Event} from "../../dto/event";
import {MenuService} from "../../menu.service";
import * as moment from "moment";
import {MenuOrder} from "../../dto/menuOrder";

@Component({
  selector: 'app-menu-order',
  templateUrl: './menu-order.component.html',
  styleUrls: ['./menu-order.component.css']
})
export class MenuOrderComponent implements OnInit {
  isLinear = false;
  menuFormGroup: FormGroup;
  orderDetailsFormGroup: FormGroup;

  menuCategories: MenuCategoryDto[];
  events: Event[];
  order = new MenuOrder();
  orderComplete: boolean;

  constructor(private menuService: MenuService, private _formBuilder: FormBuilder) {}

  ngOnInit() {
    this.orderComplete = false;
    this.menuFormGroup = this._formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.orderDetailsFormGroup = this._formBuilder.group({
      secondCtrl: ['', Validators.required]
    });
    this.order.food = {};
    this.order.foodPrice = {};
    this.order.event = 0;
    this.retrieveMenuData()
  }

  private retrieveMenuData() {
    this.menuService.getMenuPageSummary().subscribe(menuPage => {
      this.menuCategories = menuPage.categories;
      this.menuCategories.forEach(mainCategory => {
        mainCategory.categories.forEach(category => {
          if (category.menuItems) {
            category.menuItems.forEach(item => {
              item.count = 0;
            })
          }
        })
      });
      this.events = menuPage.events;
      this.events.forEach(event => {
        event.startEvent = moment(event.startEvent).format('D MMMM');
      })
    });
  }

  private submitOrder(){
    this.menuService.storeOrder(this.order)
      .subscribe(response => {
        this.order = response;
        this.orderComplete = true;
      });
  }

  payOrder() {
    this.menuService.storeOrder(this.order)
      .subscribe(response => {
        if (response.id > 0) {
          this.order = response;
          const url = "/api/order/bepaid/pay/" + this.order.id;
          window.open(url, '_blank').focus();
        } else {
          debugger;
        }
      });

  }
}
