import {Component, OnInit} from '@angular/core';
import {MenuItemPriceDto} from "../../dto/menuItemPriceDto";
import {MenuCategoryDto} from "../../dto/menuCategoryDto";
import {MenuService} from "../../menu.service";

@Component({
  selector: 'app-menu-editor',
  templateUrl: './menu-editor.component.html',
  styleUrls: ['./menu-editor.component.css']
})
export class MenuEditorComponent implements OnInit {

  categories: MenuCategoryDto[];
  orderFood: any;

  constructor(private menuService: MenuService) {
  }

  ngOnInit() {
    this.loadMenu();
  }

  loadMenu() {
    this.menuService.getMenu().subscribe(c => {
      this.categories = c;
    });
  }
}
