import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { AppRoutingModule } from '../app-routing.module';
import { Car } from '../car';
import { CarService } from '../car.service';

import { NavbarComponent } from './navbar.component';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavbarComponent ],
      imports: [
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
    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should search car', () => {
    expect(component.search).toBeTruthy();
  });

});

class CarServiceStub{
  getSingle(id: number): Observable<any> {
    return of(Car);
  }
}
