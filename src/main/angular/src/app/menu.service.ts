import {Injectable, isDevMode} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import {MenuPageSummary} from "./dto/menuPageSummary";
import {MenuOrder} from "./dto/menuOrder";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class MenuService {
  private menuSummaryUrl: string;
  private menuOrderUrl: string;

  constructor(private http: HttpClient) {
    this.menuSummaryUrl = '/api/menu/summary';
    this.menuOrderUrl = '/api/menu/order'
  }

  storeOrder(order: MenuOrder): Observable<MenuOrder> {
    return this.http.post<MenuOrder>(this.menuOrderUrl, JSON.stringify(order), httpOptions)
      .pipe(
        tap(orderResponse => this.log(`store Order`)),
        catchError(this.handleError('storeOrder', new MenuOrder()))
      );
  }

  getMenuItems(): Observable<MenuPageSummary> {
    return this.http.get<MenuPageSummary>(this.menuSummaryUrl)
      .pipe(
        tap(menuItems => this.log(`fetched events to Main Page`)),
        catchError(this.handleError('getMenuItems', new MenuPageSummary()))
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

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }
}
