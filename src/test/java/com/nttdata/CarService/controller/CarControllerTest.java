package com.nttdata.CarService.controller;

import com.nttdata.CarService.entity.Car;
import com.nttdata.CarService.handler.CarDataHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class CarControllerTest {

    @InjectMocks
    static CarController carController;

    @Mock
    static CarDataHandler carDataHandler;

    @BeforeAll
    static void init(){
        carDataHandler = Mockito.mock(CarDataHandler.class);
        carController = new CarController(carDataHandler);
    }

    @Test
    void createCar() {
        Car car = new Car();
        car.setMarke("BMW");
        car.setModell("M4");
        car.setLeistung(480);
        car.setFarbe("schwarz");
        Mockito.when(carDataHandler.createCar(any(Car.class))).thenReturn(car);
        assertEquals(HttpStatus.CREATED, carController.createCar(car).getStatusCode());
    }

    @Test
    void listSelectedCar() {
    }


    @Test
    @Disabled
    void addCarProperties() {
        Car car = new Car();
        car.setMarke("BMW");
        car.setModell("M4");
        car.setLeistung(480);
        car.setFarbe("schwarz");
        Mockito.when(carDataHandler.createCar(any(Car.class))).thenReturn(car);
        assertEquals(HttpStatus.CREATED, carController.createCar(car).getStatusCode());

        Car updatedCar = new Car();
        updatedCar.setId(carDataHandler.getCarList().get(car.getId()).getId());
        updatedCar.setMarke("BMW");
        updatedCar.setModell("M4");
        updatedCar.setLeistung(480);
        updatedCar.setFarbe("schwarz");
        Mockito.when(carDataHandler.editCar(any(Car.class))).thenReturn(updatedCar);
        assertEquals(HttpStatus.OK, carController.addCarProperties(updatedCar).getStatusCode());
    }

    @Test
    @Disabled
    void deleteCarJSON() {
        Car car = new Car();
        car.setMarke("BMW");
        car.setModell("M4");
        car.setLeistung(480);
        car.setFarbe("schwarz");
        Mockito.when(carDataHandler.createCar(any(Car.class))).thenReturn(car);
        assertEquals(HttpStatus.CREATED, carController.createCar(car).getStatusCode());

        Car deleteCar = new Car();
        deleteCar.setId(carDataHandler.getCarList().get(car.getId()).getId());
        //Mockito.when(carDataHandler.deleteCar(car.getId()));
        //Mockito.doThrow(carDataHandler.deleteCar(car.getId())).
        assertEquals(HttpStatus.NO_CONTENT, carController.deleteCarJSON(deleteCar));
    }
}