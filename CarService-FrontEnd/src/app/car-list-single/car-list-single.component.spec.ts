import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarListSingleComponent } from './car-list-single.component';

describe('CarListSingleComponent', () => {
  let component: CarListSingleComponent;
  let fixture: ComponentFixture<CarListSingleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarListSingleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarListSingleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
