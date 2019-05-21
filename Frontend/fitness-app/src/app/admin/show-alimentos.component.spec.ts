import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAlimentosComponent } from './show-alimentos.component';

describe('ShowAlimentosComponent', () => {
  let component: ShowAlimentosComponent;
  let fixture: ComponentFixture<ShowAlimentosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAlimentosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAlimentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
