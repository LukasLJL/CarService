import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from '../car.service';
import { Car } from '../car';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {
  cars: Car[];

  constructor(private carService: CarService, private router: Router) { }

  @Input() car: Car;

  ngOnInit(): void {
    this.carService.getAll().subscribe((data: Car[]) => {
      this.cars = data;
    });
  }

  deleteCar(id: number): void {
    this.carService.deleteCar(id);
    this.ngOnInit();
  }

  editCar(id: number): void {
    this.router.navigate(['/car-edit'], { queryParams: { id: id } })
  }
}