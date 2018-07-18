import { TestBed, inject } from '@angular/core/testing';

import { PhotoReportService } from './photo-report.service';

describe('PhotoReportService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PhotoReportService]
    });
  });

  it('should be created', inject([PhotoReportService], (service: PhotoReportService) => {
    expect(service).toBeTruthy();
  }));
});
