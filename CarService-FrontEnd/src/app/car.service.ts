import { Inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from './car';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  private server_url = 'http://backend-app/backend';



  private carService_list = this.server_url + '/list/';
  private carService_add = this.server_url + '/create/';
  private carService_edit = this.server_url + '/edit/';
  private carService_delete = this.server_url + '/delete/';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Car[]> {
    return this.http.get<Car[]>(this.carService_list);
  }

  getSingle(id: number): Observable<Car> {
    return this.http.get<Car>(this.carService_list + id);
  }

  addCar(car: any): Observable<any> {
    return this.http.post<Car>(this.carService_add, car);
  }

  editCar(car: any): Observable<any>{
    return this.http.put<Car>(this.carService_edit, car);
  }

  deleteCar(id: number):void {
    this.http.delete(this.carService_delete+id).subscribe();
  }

}
