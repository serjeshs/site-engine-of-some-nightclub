import { TestBed, inject } from '@angular/core/testing';

import { OrderTicketService } from './order-ticket.service';

describe('OrderTicketService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OrderTicketService]
    });
  });

  it('should be created', inject([OrderTicketService], (service: OrderTicketService) => {
    expect(service).toBeTruthy();
  }));
});
