import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MenuOrder} from "../dto/menuOrder";
import {MenuService} from "../menu.service";
import {MenuCategoryDto} from "../dto/menuCategoryDto";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  uuid: string;
  order: MenuOrder;
  menuCategories: MenuCategoryDto[];

  constructor(private activatedRoute: ActivatedRoute, private menuService: MenuService) {
    this.menuCategories = [];
    this.order = new MenuOrder();
    this.order.foodPrice = {};
    this.order.food = {};
  }

  ngOnInit() {
    this.uuid = this.activatedRoute.snapshot.paramMap.get('uuid');
    this.menuService.getMenu().subscribe(c => {
      this.menuCategories = c;
    });
    this.menuService.getOrderByUuid(this.uuid)
      .subscribe(order => {
          if (order.id) {
            this.order = order
          }
        }
      )
  }
}
