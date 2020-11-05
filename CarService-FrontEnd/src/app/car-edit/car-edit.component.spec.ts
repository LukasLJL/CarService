import { HttpClientModule } from '@angular/common/http';
import { isFormattedError } from '@angular/compiler';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { AppRoutingModule } from '../app-routing.module';
import { Car } from '../car';
import { CarService } from '../car.service';

import { CarEditComponent } from './car-edit.component';

describe('CarEditComponent', () => {
  let component: CarEditComponent;
  let fixture: ComponentFixture<CarEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarEditComponent ],
      imports: [
        ReactiveFormsModule,
        HttpClientModule,
        AppRoutingModule,
      ],
      providers: [
        {provide: CarService, useClass: CarServiceStub}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


  it('should update car', () => {
    expect(component.updateCar).toBeTruthy();
  });

  it('should cancelUpdate car', () => {
    expect(component.cancelUpdate).toBeTruthy();
  });

  it('should listSingle car', () => {
    expect(component.listSingle).toBeTruthy();
  });


});

class CarServiceStub{
  
  getSingle(id: number): Observable<any>{
    return of(Car);    
  }

}
