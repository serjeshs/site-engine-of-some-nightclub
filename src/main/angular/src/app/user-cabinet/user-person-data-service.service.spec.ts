import { TestBed } from '@angular/core/testing';

import { UserPersonDataService } from './user-person-data.service';

describe('UserPersonDataServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserPersonDataService = TestBed.get(UserPersonDataService);
    expect(service).toBeTruthy();
  });
});
