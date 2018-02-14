import {MenuItemPriceDto} from "./menuItemPriceDto";

export class MenuCategoryDto {
  id: number;
  name: string;
  description: string;
  categories: MenuCategoryDto[];
  menuItems: MenuItemPriceDto[];
}
