import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { AppRoutingModule } from '../app-routing.module';
import { Car } from '../car';
import { CarService } from '../car.service';

import { CarListSingleComponent } from './car-list-single.component';

describe('CarListSingleComponent', () => {
  let component: CarListSingleComponent;
  let fixture: ComponentFixture<CarListSingleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarListSingleComponent],
      imports: [
        HttpClientModule,
        AppRoutingModule
      ],
      providers: [
        { provide: CarService, useClass: CarServiceStub }
      ]
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

  it('should edit car', () => {
    expect(component.editCar).toBeTruthy();
  });

  it('should delete car', () => {
    expect(component.deleteCar).toBeTruthy();
  });

  it('should list car', () => {
    expect(component.ngOnInit).toBeTruthy();
  });
});

class CarServiceStub {
  getSingle(id: number): Observable<any> {
    return of(Car);
  }
}
