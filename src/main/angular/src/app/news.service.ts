import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import {NewsItemDto} from "./dto/newsItemDto";


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class NewsService {
  private newsSummaryUrl: string;
  private newsByIdUrl: string;
  private host: string;

  constructor(private http: HttpClient) {
    this.host = '';
    // this.host = 'http://localhost:28010/';
    this.newsSummaryUrl = this.host  + '/api/news/summary'
    this.newsByIdUrl = this.host  + '/api/news/';
  }

  getNewsItems(): Observable<NewsItemDto[]> {
    return this.http.get<NewsItemDto[]>(this.newsSummaryUrl)
      .pipe(
        tap(newsItems => this.log(`fetched news to News Page`)),
        catchError(this.handleError('getNewsItems', []))
      );
  }

  getNewsItem(id: number): Observable<NewsItemDto> {
    const options = id ?
      {params: new HttpParams().set('id', <string>id)} : {};

    return this.http.get<NewsItemDto>(this.newsByIdUrl, options)
      .pipe(
        tap(newsItems => this.log(`fetched newsItem`)),
        catchError(this.handleError('getNewsItem', new NewsItemDto()))
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
