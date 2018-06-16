import {Component, OnInit} from '@angular/core';
import {MenuService} from "../menu.service";
import {MenuCategoryDto} from "../dto/menuCategoryDto";
import {MenuItemPriceDto} from "../dto/menuItemPriceDto";
import {Event} from "../dto/event";
import * as moment from "moment";
import {MenuOrder} from "../dto/menuOrder";
import {Table} from "../dto/table";

export class MenuWrapper {
  edited: boolean;
}

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  // private model : MenuOrder;
  model = new MenuOrder();
  private menuCategories: MenuCategoryDto[];
  private events: Event[];
  private tableNumbers: Table[];
  private wrapper: MenuWrapper;

  constructor(private menuService: MenuService) {
  }

  ngOnInit() {
    this.buildMenuPage();
    this.model.food = {};
    this.wrapper = new MenuWrapper();
    this.wrapper.edited = true;
    this.tableNumbers = Array<Table>();
    this.model.event = 0;
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

  order() {
    if (this.model.rulesCheck) {
      this.menuService.storeOrder(this.model)
        .subscribe(response => {
          this.model = <MenuOrder>response;
          this.wrapper.edited = false;
        });
    } else {
      // this.toasterService.pop('error', 'Правила', 'Для посещения клуба вы должны принять его правила');
    }
  }

  changeEvent() {
    this.menuService.getAvailableTables(this.model.event).subscribe(responseArray => {
      let tableNumberForm = Array<Table>();
      if (Array.isArray(responseArray)) {
        responseArray.forEach(i => {
          let table = new Table();
          table.id = i;
          table.name = "Стол №" + i;
          tableNumberForm.push(table)
        });
      }
      this.tableNumbers = tableNumberForm;
    });
  }

  private buildMenuPage() {
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

  private storeCountInModel(item: MenuItemPriceDto) {
    this.model.food[item.itemPriceId] = item.count;
  }
}
