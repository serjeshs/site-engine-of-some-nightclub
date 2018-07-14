import {Component, OnInit} from '@angular/core';
import {MenuCategoryDto} from "../../dto/menuCategoryDto";
import {MenuService} from "../../menu.service";

class MenuEditorWrapper {
  categoryEditDto: MenuCategoryDto;
}

@Component({
  selector: 'app-menu-editor',
  templateUrl: './menu-editor.component.html',
  styleUrls: ['./menu-editor.component.css']
})
export class MenuEditorComponent implements OnInit {

  categories: MenuCategoryDto[];
  orderFood: any;
  editCategoryId: number;
  editWrapper: MenuEditorWrapper;

  constructor(private menuService: MenuService) {
  }

  ngOnInit() {
    this.editCategoryId = -1;
    this.editWrapper = new MenuEditorWrapper();
    this.editWrapper.categoryEditDto = new MenuCategoryDto();
    this.loadMenu();
  }

  loadMenu() {
    this.menuService.getMenu().subscribe(c => {
      this.categories = c;
    });
  }

  onClickeditCategory(category: MenuCategoryDto) {
    this.editCategoryId = category.id;
    this.editWrapper.categoryEditDto = category;
  }

  onClickSaveCategory(category: MenuCategoryDto) {
    console.log(category);
    this.editCategoryId = 0;
    this.menuService.saveCategory(category)
      .subscribe(responce => {
        if (responce.success) {
          this.loadMenu();
          this.editCategoryId = -1;
        }
      });
  }

  onClickNewCategory(categoryMain: MenuCategoryDto) {
    this.editCategoryId = -1;
    this.editWrapper.categoryEditDto = new MenuCategoryDto();
    this.editWrapper.categoryEditDto.parentId = categoryMain.id;
    this.onClickeditCategory(this.editWrapper.categoryEditDto);
  }
}
