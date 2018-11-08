import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {OrderTicketsComponent} from './order-tickets.component';

describe('OrderTicketsComponent', () => {
  let component: OrderTicketsComponent;
  let fixture: ComponentFixture<OrderTicketsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [OrderTicketsComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
