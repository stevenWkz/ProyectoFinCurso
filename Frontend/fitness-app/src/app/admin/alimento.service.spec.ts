import { TestBed } from '@angular/core/testing';

import { AlimentoService } from './alimento.service';

describe('AlimentoService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AlimentoService = TestBed.get(AlimentoService);
    expect(service).toBeTruthy();
  });
});
