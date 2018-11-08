import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable()
export class OrderTicketService {

  constructor(private http: HttpClient) {

  }

  getTables(eventId: number) {
    const url = '/api/tickets/tables?eventId=' + eventId;
    return this.http.get(url)
  }
}
