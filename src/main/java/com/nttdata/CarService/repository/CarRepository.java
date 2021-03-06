package com.nttdata.CarService.repository;

import com.nttdata.CarService.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface CarRepository extends CrudRepository <Car, Integer> {

}
