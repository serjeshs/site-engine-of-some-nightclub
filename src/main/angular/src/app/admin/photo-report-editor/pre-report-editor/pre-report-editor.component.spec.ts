import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreReportEditorComponent } from './pre-report-editor.component';

describe('PreReportEditorComponent', () => {
  let component: PreReportEditorComponent;
  let fixture: ComponentFixture<PreReportEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreReportEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreReportEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
