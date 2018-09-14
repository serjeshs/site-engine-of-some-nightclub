import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreReportsGalleryComponent } from './pre-reports-gallery.component';

describe('PreReportsGalleryComponent', () => {
  let component: PreReportsGalleryComponent;
  let fixture: ComponentFixture<PreReportsGalleryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreReportsGalleryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreReportsGalleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
