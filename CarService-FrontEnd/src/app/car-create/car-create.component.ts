import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CarService } from '../car.service';
import { Car } from '../car'
import { Router } from '@angular/router';

@Component({
  selector: 'app-car-create',
  templateUrl: './car-create.component.html',
  styleUrls: ['./car-create.component.css']
})
export class CarCreateComponent implements OnInit {

  car: Car;

  constructor(private carService: CarService, private router: Router) {
    this.car = new Car();
   }

  ngOnInit(): void {
  }

  createCar(form: NgForm): void{
    this.carService.addCar(form).subscribe( data => {      
    },
    error => console.log(error));
    this.router.navigate(['/car-list']);
  }
}
