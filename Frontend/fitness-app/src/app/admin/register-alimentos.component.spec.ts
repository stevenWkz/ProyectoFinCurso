import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterAlimentosComponent } from './register-alimentos.component';

describe('RegisterAlimentosComponent', () => {
  let component: RegisterAlimentosComponent;
  let fixture: ComponentFixture<RegisterAlimentosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterAlimentosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterAlimentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
