import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { CarService } from '../car.service';
import { Car } from '../car'
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-car-edit',
  templateUrl: './car-edit.component.html',
  styleUrls: ['./car-edit.component.css']
})
export class CarEditComponent implements OnInit {
  @Input() id: number;

  car: Car;

  carForm = this.fb.group({
    id: [],
    marke: [],
    model: [],
    farbe: [],
    leistung: [],
    drehmoment: [],
    gewicht: [],
    tueren: [],
    klasse: [],
    motor_art: []
  });

  constructor(private carService: CarService, private router: ActivatedRoute, private fb: FormBuilder, private location: Location) {
    this.car = new Car();
  }

  ngOnInit(): void {
    this.router.queryParams.subscribe(params => {
      this.id = params['id'];
      this.carForm.controls['id'].setValue(this.id);
      this.listSingle(this.id);
    });
  }


  updateCar(): void {
    if (this.id != null) {
      this.carForm.controls['id'].setValue(this.id);
    }
    this.carService.editCar(this.carForm.value).subscribe(data => {
      console.log(data);
    },
      error => console.log(error));
    this.location.back();

  }

  cancelUpdate(): void {
    this.location.back();
  }

  listSingle(id: number): void {
    this.carService.getSingle(id).subscribe(data => {
      this.car = data;
    });
  }

}


