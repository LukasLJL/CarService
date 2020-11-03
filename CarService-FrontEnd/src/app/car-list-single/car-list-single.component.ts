import { Component, OnInit, Input } from '@angular/core';
import { CarService } from '../car.service';
import { Car } from '../car';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-car-list-single',
  templateUrl: './car-list-single.component.html',
  styleUrls: ['./car-list-single.component.css']
})
export class CarListSingleComponent implements OnInit {
  @Input() id: number;

  car_single: Car;

  constructor(private carService: CarService, private routerA: ActivatedRoute, private router: Router) {
   }

  ngOnInit(): void {
    this.routerA.queryParams.subscribe(params => {
      this.id = params['id'];
      this.listSingle(this.id);
    });
  }

  listSingle(id: number): void {
    this.carService.getSingle(id).subscribe(data => {
      this.car_single = data;
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
