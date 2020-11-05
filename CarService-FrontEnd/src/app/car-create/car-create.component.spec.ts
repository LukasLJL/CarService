import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, NgForm } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { AppRoutingModule } from '../app-routing.module';
import { RouterTestingModule } from "@angular/router/testing";
import { CarService } from '../car.service';
import { Location } from '@angular/common';

import { CarCreateComponent } from './car-create.component';
import { Component } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Car } from '../car';

describe('CarCreateComponent', () => {
  let component: CarCreateComponent;
  let fixture: ComponentFixture<CarCreateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CarCreateComponent, DummyComponent],
      imports: [
        HttpClientModule,
        FormsModule,
        AppRoutingModule,
        RouterTestingModule.withRoutes([
          { path: 'car-list', component: DummyComponent }
        ])
      ],
      providers: [
        { provide: CarService, useClass: CarServiceStub }
      ]
    })
      .compileComponents();
    fixture = TestBed.createComponent(CarCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should create a car', () => {
    expect(component.createCar).toBeTruthy();
  });

  it('should be a create car button', () => {
    const button = fixture.debugElement.queryAll(By.css('button'));
    const nativeButton: HTMLButtonElement = button[0].nativeElement;
    expect(nativeButton.textContent).toMatch('Add Car');
  });

  it('should navigate to carlist after button click', () => {
    const location = TestBed.get(Location);
    const button = fixture.debugElement.queryAll(By.css('button'));
    const nativeButton: HTMLButtonElement = button[0].nativeElement;
    nativeButton.click();
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      expect(location.path()).toBe('/car-list');
    });
  });
  
});


class CarServiceStub {
  addCar(car: any): Observable<any> {
    return of(Car);
  }

  getSingle(id: number): Observable<any> {
    return of(Car);
  }
}

@Component({ template: '' })
class DummyComponent { }
