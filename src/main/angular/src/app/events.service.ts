import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable, of as observableOf} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {MainEventPage} from "./dto/mainEventPage";
import {EventReport} from "./dto/eventGallery";
import {Event} from "./dto/event";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class EventsService {
  private eventsMainPageUrl = '/api/page/main';
  private eventReportsUrl = '/api/media/summary';
  private eventsRestAdminUrl = '/api/admin/events';
  private eventsRestUrl = '/api/events';

  constructor(private http: HttpClient) {
  }

  getMainPageEvents(): Observable<MainEventPage> {
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

  getEvents(page: number): Observable<Event[]> {
    let url = this.eventsRestAdminUrl + '?page=' + page;
    return this.http.get<Event[]>(url).pipe(
      tap(events => this.log(`fetched events to Admin Page`)),
      catchError(this.handleError('getEvents', []))
    );
  }

  save(event: Event): Observable<Event> {
    return this.http.post<Event>(this.eventsRestAdminUrl, JSON.stringify(event), httpOptions)
      .pipe(
        catchError(this.handleError('saveEvent', new Event()))
      );
  }

  getEvent(eventId: number): Observable<Event> {
    let url = this.eventsRestUrl + '?id=' + eventId;
    return this.http.get<Event>(url)
      .pipe();
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
      return observableOf(result as T);
    };
  }

  private log(message: string) {
    console.log(message);
  }
}
