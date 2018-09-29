import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RulesPayComponent } from './rules-pay.component';

describe('RulesPayComponent', () => {
  let component: RulesPayComponent;
  let fixture: ComponentFixture<RulesPayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RulesPayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RulesPayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
