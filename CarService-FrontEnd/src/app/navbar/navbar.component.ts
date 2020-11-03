import { Component, Input, OnInit } from '@angular/core';
import { CarService } from '../car.service';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isCarList: boolean;
  isCarCreate: boolean;
  isCarEdit: boolean;



  constructor(private carService: CarService, private location: Location, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    if (this.location.isCurrentPathEqualTo('/car-list')) {
      this.isCarList = true;
      this.isCarCreate = false;
      this.isCarEdit = false;
    } else if (this.location.isCurrentPathEqualTo('/car-create')) {
      this.isCarList = false;
      this.isCarCreate = true;
      this.isCarEdit = false;
    } else if (this.location.isCurrentPathEqualTo('/car-edit')) {
      this.isCarList = false;
      this.isCarCreate = false;
      this.isCarEdit = true;
    } else{
      this.isCarList = false;
      this.isCarCreate = false;
      this.isCarEdit = false;
    } 
  }

  search(id: number): void {
    this.router.navigate(['/car-list-single'], { queryParams: { id: id } });
  }

}
