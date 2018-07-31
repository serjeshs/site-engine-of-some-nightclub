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
  recommendation: string;
}

export class EventListResult {
  items: Event[];
  total: number
}
