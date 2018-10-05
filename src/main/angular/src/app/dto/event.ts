export class Event {
  id: number;
  cost: number;
  costText: string;
  description: string;
  startEvent: string;
  endEvent: string;
  name: string;
  coverUri: string;
  buyTicketUrl: string;
  recommendation: boolean;
  republicPay: boolean;
}

export class EventListResult {
  items: Event[];
  total: number
}
