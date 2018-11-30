import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {TicketOrder} from "../../dto/ticketOrder";
import {ResponseEntity} from "../../dto/ResponseEntity";

@Injectable()
export class OrderTicketService {

  constructor(private http: HttpClient) {

  }

  getTables(eventId: number) {
    const url = '/api/tickets/tables?eventId=' + eventId;
    return this.http.get(url)
  }

  payOrder(ticketOrder: TicketOrder) {
    const url = '/api/tickets/bookandpay';
    return this.http.post<ResponseEntity>(url, ticketOrder)
  }

  getTicketsReport(eventId: number) {
    const url = '/api/tickets/report/event?eventId=' + eventId;
    return this.http.get<ResponseEntity>(url)
  }
}
