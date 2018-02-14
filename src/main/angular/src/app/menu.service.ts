import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, map, tap} from 'rxjs/operators';
import {MenuCategoryDto} from "./dto/menuCategoryDto";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class MenuService {
  private menuSummaryUrl: string;

  constructor(private http: HttpClient) {
    // this.menuSummaryUrl = 'http://localhost:28010/api/menu/summary'
    this.menuSummaryUrl = '/api/menu/summary'
  }

  getMenuItems(): Observable<MenuCategoryDto[]> {
    return this.http.get<MenuCategoryDto[]>(this.menuSummaryUrl)
      .pipe(
        tap(menuItems => this.log(`fetched events to Main Page`)),
        catchError(this.handleError('getMenuItems', {}))
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
