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

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    HashMap<Integer, Car> carList;

    @InjectMocks
    static CarController carController;

    @Mock
    static CarDataHandler carDataHandler;

    @BeforeAll
    static void init() {
        carDataHandler = Mockito.mock(CarDataHandler.class);
        carController = new CarController(carDataHandler);
    }

    @Test
    void createCar() {
        //setup mock car
        Car emptyCar = mock(Car.class);
        when(emptyCar.getId()).thenReturn(42);

        when(carDataHandler.createCar(any(Car.class))).thenReturn(emptyCar);
        assertEquals(HttpStatus.CREATED, carController.createCar(emptyCar).getStatusCode());
    }

    @Test
    void listSelectedCar() {
        //setup mock car
        Car emptyCar = mock(Car.class);
        assertEquals(null, carController.listSelectedCar((emptyCar.getId())));
    }


    @Nested
    class editCar {

        @Test
        void editCar() {
            //setup mock car
            Car emptyCar = mock(Car.class);
            when(emptyCar.getId()).thenReturn(42);

            //fix errors in Controller function
            HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(true);
            when(carDataHandler.getCarList()).thenReturn(carList);

            //check if all is true, that it works
            assertEquals(HttpStatus.OK, carController.addCarProperties(emptyCar).getStatusCode());
        }

        @Test
        void editCar_InvalidID() {
            //setup mock car
            Car emptyCar = mock(Car.class);
            when(emptyCar.getId()).thenReturn(42);

            HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(false);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.NOT_FOUND, carController.addCarProperties(emptyCar).getStatusCode());

        }

        @Test
        void editCar_CarListEmpty() {
            //setup mock car
            Car emptyCar = mock(Car.class);
            when(emptyCar.getId()).thenReturn(42);

            HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);
            when(carList.isEmpty()).thenReturn(true);
            when(carList.containsKey(anyInt())).thenReturn(false);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.NOT_FOUND, carController.addCarProperties(emptyCar).getStatusCode());

        }

        @Test
        void editCar_withoutID() {
            //setup mock car
            Car emptyCar = mock(Car.class);
            when(emptyCar.getId()).thenReturn(null);

            HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(true);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.BAD_REQUEST, carController.addCarProperties(emptyCar).getStatusCode());

        }
    }


    @Nested
    class deleteCar {
        @Test
        void deleteCar() {
            //setup Mock Hashmap to avoid error in deleteCar function from Controller
            HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(true);

            //setup empty mock Car
            Car emptyCar = mock(Car.class);
            when(emptyCar.getId()).thenReturn(7);

            //successfully deleted
            when(carDataHandler.getCarList()).thenReturn(carList);
            assertEquals(HttpStatus.NO_CONTENT, carController.deleteCarJSON(emptyCar).getStatusCode());
        }

        @Test
        void deleteCar_InvalidID() {
            //setup empty mock Car
            Car emptyCar = mock(Car.class);
            when(emptyCar.getId()).thenReturn(7);

            HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);

            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(false);

            when(carDataHandler.getCarList()).thenReturn(carList);
            assertEquals(HttpStatus.NOT_FOUND, carController.deleteCarJSON(emptyCar).getStatusCode());
        }

        @Test
        void deleteCar_NoCarToDelete() {
            //setup empty mock Car
            Car emptyCar = mock(Car.class);
            when(emptyCar.getId()).thenReturn(7);

            HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);

            when(carList.isEmpty()).thenReturn(true);
            when(carList.containsKey(anyInt())).thenReturn(false);

            when(carDataHandler.getCarList()).thenReturn(carList);
            assertEquals(HttpStatus.NOT_FOUND, carController.deleteCarJSON(emptyCar).getStatusCode());
        }
    }

}