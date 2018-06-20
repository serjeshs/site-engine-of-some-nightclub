import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import {Observable} from 'rxjs';
import {EventReport} from "./dto/eventGallery";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class EventReportService {
  private eventReportsUrl = '/api/media/summary';
  private eventReportUrl = '/api/event/report';


  constructor(private http: HttpClient) {
  }

  getReportEvents(): Observable<EventReport[]> {
    return this.http.get<EventReport[]>(this.eventReportsUrl).pipe();
  }

  getReportEvent(id: number): Observable<EventReport> {
    let url = this.eventReportUrl + '?id=' + id;
    return this.http.get<EventReport>(url).pipe();
  }
}
