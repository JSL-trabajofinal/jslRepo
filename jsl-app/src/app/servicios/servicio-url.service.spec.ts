import { TestBed } from '@angular/core/testing';

import { ServicioUrlService } from './servicio-url.service';

describe('ServicioUrlService', () => {
  let service: ServicioUrlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicioUrlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
