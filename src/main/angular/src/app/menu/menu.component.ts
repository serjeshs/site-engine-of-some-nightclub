import {Component, OnInit} from '@angular/core';
import {MenuService} from "../menu.service";
import {Observable} from "rxjs/Observable";
import {MenuCategoryDto} from "../dto/menuCategoryDto";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  private menuCategories: MenuCategoryDto[];

  constructor(private menuService: MenuService) {
  }

  ngOnInit() {
    this.buildMenuPage();
  }

  private buildMenuPage() {
    this.menuService.getMenuItems().subscribe(categories => {
      this.menuCategories = categories;

    });
  }
}
