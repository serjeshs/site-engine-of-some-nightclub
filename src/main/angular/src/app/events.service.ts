import {Injectable, isDevMode} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, tap} from 'rxjs/operators';
import {MainEventPage} from "./dto/mainEventPage";
import {EventReport} from "./dto/eventGallery";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class EventsService {
  private eventsMainPageUrl = '';
  private eventReportsUrl = '';

  constructor(private http: HttpClient) {
    this.eventsMainPageUrl = '/api/page/main';
    this.eventReportsUrl = '/api/media/summary';

  }

  /** GET heroes from the server */
  getEvents(): Observable<MainEventPage> {
    return this.http.get<MainEventPage>(this.eventsMainPageUrl)
      .pipe(
        tap(mainEventsPage => this.log(`fetched events to Main Page`)),
        catchError(this.handleError('geEvents', new MainEventPage()))
      );
  }

  getReportEvents(): Observable<EventReport[]> {
    return this.http.get<EventReport[]>(this.eventReportsUrl)
      .pipe(
        tap(eventsReport => this.log(`fetched eventsReport to Media Page`)),
        catchError(this.handleError('getReportEvents', []))
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
