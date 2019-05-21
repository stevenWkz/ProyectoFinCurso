import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAlimentosComponent } from './update-alimentos.component';

describe('UpdateAlimentosComponent', () => {
  let component: UpdateAlimentosComponent;
  let fixture: ComponentFixture<UpdateAlimentosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateAlimentosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateAlimentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
