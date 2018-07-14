import {MenuItemPriceDto} from "./menuItemPriceDto";

export class MenuCategoryDto {
  id: number;
  name: string;
  description: string;
  parentId : number;
  categories: MenuCategoryDto[];
  menuItems: MenuItemPriceDto[];
}
