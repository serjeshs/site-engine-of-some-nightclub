import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MenuOrder} from "../dto/menuOrder";
import {MenuService} from "../menu.service";
import {MenuCategoryDto} from "../dto/menuCategoryDto";
import {forkJoin} from "rxjs";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  uuid: string;
  order: MenuOrder;
  menuCategories: MenuCategoryDto[];
  showOrder: boolean;

  constructor(private activatedRoute: ActivatedRoute, private menuService: MenuService) {
    this.menuCategories = [];
    this.order = new MenuOrder();
    this.order.foodPrice = {};
    this.order.food = {};
  }

  ngOnInit() {
    this.showOrder = false;
    this.uuid = this.activatedRoute.snapshot.paramMap.get('uuid');
    let menuCategories;
    let menuOrder;
    forkJoin([
      this.menuService.readMenu().pipe(
        tap(categories => {
          menuCategories = categories
        })
      ),
      this.menuService.readOrderByUuid(this.uuid).pipe(
        tap(order => {menuOrder = order}
        )
      )
    ]).subscribe(data => {
      if (menuOrder.id > 0) {
        this.menuCategories = menuCategories;
        this.order = <MenuOrder>menuOrder
        this.showOrder = true;
      }
    });
  }
}
