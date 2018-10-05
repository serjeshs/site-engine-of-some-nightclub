import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable, of as observableOf} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {MenuPageSummary} from "./dto/menuPageSummary";
import {MenuOrder} from "./dto/menuOrder";
import {MenuCategoryDto} from "./dto/menuCategoryDto";
import {ResponseEntity} from "./dto/ResponseEntity";
import {MenuItemPriceDto} from "./dto/menuItemPriceDto";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class MenuService {
  private menuSummaryUrl: string;
  private menuOrderUrl: string;
  private availableTablesUrl: string;
  private ordersUrl: string;
  private menuUrl: string;
  private adminCategoryUrl: string;
  private adminItemUrl: string;

  constructor(private http: HttpClient) {
    this.menuSummaryUrl = '/api/menu/summary';
    this.menuOrderUrl = '/api/menu/order';
    this.availableTablesUrl = '/api/menu/availibletables';
    this.ordersUrl = '/api/menu/admin/orders';
    this.adminCategoryUrl = '/api/menu/admin/category';
    this.menuUrl = '/api/menu/food';
    this.adminItemUrl = '/api/menu/admin/item'
  }

  storeOrder(order: MenuOrder): Observable<MenuOrder> {
    return this.http.post<MenuOrder>(this.menuOrderUrl, JSON.stringify(order), httpOptions)
      .pipe(
        tap(orderResponse => this.log(`store Order`)),
        catchError(this.handleError('storeOrder', new MenuOrder()))
      );
  }

  getMenuPageSummary(): Observable<MenuPageSummary> {
    return this.http.get<MenuPageSummary>(this.menuSummaryUrl)
      .pipe(
        tap(menuItems => this.log(`fetched events to Main Page`)),
        catchError(this.handleError('getMenuPageSummary', new MenuPageSummary()))
      );
  }

  getAvailableTables(event: number) {
    let url = this.availableTablesUrl + "?eventId=" + event;
    return this.http.get<Number>(url)
      .pipe(
        tap(menuItems => this.log(`fetched avail tables by eventId` + JSON.stringify(menuItems))),
        catchError(this.handleError('getAvailableTables', []))
      );
  }

  getOrders(event: number) {
    let url = this.ordersUrl + "?eventId=" + event;
    return this.http.get<MenuOrder>(url)
      .pipe(
        tap(menuItems => this.log(`fetched avail tables by eventId` + JSON.stringify(menuItems))),
        catchError(this.handleError('getAvailableTables', []))
      );
  }

  getOrder(orderNumber: number) {
    let url = this.menuOrderUrl + "?orderId=" + orderNumber;
    return this.http.get<MenuOrder>(url)
      .pipe(
        tap(() => this.log(`get Order`)),
        catchError(this.handleError('getOrder', new MenuOrder()))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);
      // Let the app keep running by returning an empty result.
      return observableOf(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }

  getMenu() {
    return this.http.get<MenuCategoryDto[]>(this.menuUrl)
      .pipe(
        tap(menuItems => this.log(`fetched events to Main Page`)),
        catchError(this.handleError('getMenuPageSummary', []))
      );
  }

  readMenu() : Observable<MenuCategoryDto[]> {
    return this.http.get<MenuCategoryDto[]>(this.menuUrl);
  }



  saveCategory(category: MenuCategoryDto): Observable<ResponseEntity> {
    let url = this.adminCategoryUrl;
    return this.http.post<ResponseEntity>(url, JSON.stringify(category), httpOptions)
      .pipe();
  }

  saveItemPosition(itemPosition: MenuItemPriceDto): Observable<ResponseEntity> {
    let url = this.adminItemUrl;
    return this.http.post<ResponseEntity>(url, JSON.stringify(itemPosition), httpOptions)
      .pipe();
  }

  getOrderByUuid(uuid: string) : Observable<MenuOrder> {
    const url = '/api/private/orders/' + uuid;
    return this.http.get<MenuOrder>(url).pipe();
  }
  readOrderByUuid(uuid: string) : Observable<MenuOrder> {
    const url = '/api/private/orders/' + uuid;
    return this.http.get<MenuOrder>(url);
  }
}
