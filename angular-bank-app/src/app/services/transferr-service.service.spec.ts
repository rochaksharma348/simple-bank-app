import { TestBed } from '@angular/core/testing';

import { TransferrServiceService } from './transferr-service.service';

describe('TransferrServiceService', () => {
  let service: TransferrServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransferrServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
