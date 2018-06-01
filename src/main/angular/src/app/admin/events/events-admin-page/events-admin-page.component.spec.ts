import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsAdminPageComponent } from './events-admin-page.component';

describe('EventsAdminPageComponent', () => {
  let component: EventsAdminPageComponent;
  let fixture: ComponentFixture<EventsAdminPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsAdminPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventsAdminPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
