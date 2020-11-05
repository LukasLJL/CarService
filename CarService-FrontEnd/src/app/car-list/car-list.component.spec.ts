import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppRoutingModule } from '../app-routing.module';
import { Car } from '../car';
import { CarService } from '../car.service';
import { Observable, of } from 'rxjs';

import { CarListComponent } from './car-list.component';
import { By } from '@angular/platform-browser';
import { RouterTestingModule } from '@angular/router/testing';
import { Component } from '@angular/core';
import { Location } from '@angular/common';

describe('CarListComponent', () => {
  let component: CarListComponent;
  let fixture: ComponentFixture<CarListComponent>;
  let helper: Helper;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarListComponent, DummyComponent],
      imports: [
        HttpClientModule,
        AppRoutingModule,
        RouterTestingModule.withRoutes([
          { path: 'car-edit', component: DummyComponent }
        ])
      ],
      providers: [
        { provide: CarService, useClass: CarServiceStub }
      ]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarListComponent);
    component = fixture.componentInstance;
    helper = new Helper();
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should list cars in table', () => {
    component.cars = helper.getCars(2);
    fixture.detectChanges();
    const listCars = fixture.debugElement.queryAll(By.css('td'));
    const car1 = listCars[0];
    const car2 = listCars[1];
    const listCarsElement1: HTMLTableElement = car1.nativeElement;
    const listCarsElement2: HTMLTableElement = car2.nativeElement;
    expect(listCarsElement1.textContent).toMatch('0');
    expect(listCarsElement2.textContent).toMatch(' Audi ');
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

  getAll(): Observable<Car[]> {
    return of([]);
  }
}

class Helper {
  cars: Car[] = [];
  getCars(number: number): Car[] {
    for (let i = 0; i < number; i++) {
      this.cars.push(
        {
          id: i, marke: 'Audi', model: 'RS3', drehmoment: 450, leistung: 330,
          farbe: 'grau', gewicht: 1605, klasse: 'ad', motor_art: 'benzin', tueren: 3
        }
      );
    }
    return this.cars;
  }
}

@Component({ template: '' })
class DummyComponent { }
