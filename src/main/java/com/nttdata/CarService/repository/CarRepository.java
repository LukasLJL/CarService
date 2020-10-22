package com.nttdata.CarService.repository;

import com.nttdata.CarService.entity.Car;
import org.springframework.data.repository.CrudRepository;


public interface CarRepository extends CrudRepository <Car, Integer> {

}
