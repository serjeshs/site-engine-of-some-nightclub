import {Event} from "./event";
import {TableDto} from "../orders/order-tickets/order-tickets.component";

export class TicketOrder {
  public totalMoney : number;
  public danceFloor: number;
  public event: Event;
  public placeSeats: number;
  public name: string;
  public surname: string;
  public description: string;
  public email: string;
  public phone: string;
  public rulesCheck: boolean;
  public offerCheck: boolean;
  public tables: TableDto[];

}
