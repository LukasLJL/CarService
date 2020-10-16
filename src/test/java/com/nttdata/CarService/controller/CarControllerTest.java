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
import org.springframework.test.context.junit.jupiter.DisabledIf;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    @Mock
    HashMap<Integer, Car> carList;

    @InjectMocks
    static CarController carController;

    @Mock
    static CarDataHandler carDataHandler;

    @Mock
    static Car emptyCar;

    @BeforeAll
    static void init() {
        carDataHandler = Mockito.mock(CarDataHandler.class);
        carController = new CarController(carDataHandler);
        emptyCar = Mockito.mock(Car.class);
    }

    @Test
    @DisplayName("Create a test Car")
    void createCar() {
        when(carDataHandler.createCar(any(Car.class))).thenReturn(emptyCar);
        assertEquals(HttpStatus.CREATED, carController.createCar(emptyCar).getStatusCode());
    }

    @Nested
    @DisplayName("List all Cars")
    class listAllCars{

        HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);

        @Test
        @DisplayName("List all cars successfully")
        void listAllCars_exists(){
            when(carList.isEmpty()).thenReturn(false);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.OK, carController.listCar().getStatusCode());
        }

        @Test
        @DisplayName("List all cars while no car exists")
        void listAllCars_EmptyCarList(){
            when(carList.isEmpty()).thenReturn(true);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.NOT_FOUND, carController.listCar().getStatusCode());
        }
    }


    @Nested
    @DisplayName("List a selected Car")
    class listSelectedCar{

        HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);

        @Test
        @DisplayName("List a selected car successfully")
        void listSelectedCar_exits() {
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(true);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.OK, carController.listSelectedCar((42)).getStatusCode());
        }

        @Test
        @DisplayName("List a selected car without providing an ID")
        void listSelectedCar_WithoutID() {
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(true);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.BAD_REQUEST, carController.listSelectedCar((null)).getStatusCode());
        }

        @Test
        @DisplayName("List a selected car with an Invalid ID")
        void listSelectedCar_InvalidID() {
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(false);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.NOT_FOUND, carController.listSelectedCar((42)).getStatusCode());
        }

        @Test
        @DisplayName("List a selected car while no car exists")
        void listSelectedCar_NoCarExists() {
            when(carList.isEmpty()).thenReturn(true);
            when(carList.containsKey(anyInt())).thenReturn(false);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.NOT_FOUND, carController.listSelectedCar((42)).getStatusCode());
        }
    }


    @Nested
    @DisplayName("Every option to edit a car ")
    class editCar {

        HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);

        @BeforeEach
        void setupMockCar(){
            when(emptyCar.getId()).thenReturn(42);
        }

        @Test
        @DisplayName("Edit a car successfully")
        void editCar() {
            //fix errors in Controller function
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(true);
            when(carDataHandler.getCarList()).thenReturn(carList);

            //check if all is true, that it works
            assertEquals(HttpStatus.OK, carController.addCarProperties(emptyCar).getStatusCode());
        }

        @Test
        @DisplayName("Edit a car with an Invalid ID")
        void editCar_InvalidID() {
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(false);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.NOT_FOUND, carController.addCarProperties(emptyCar).getStatusCode());

        }

        @Test
        @DisplayName("Edit a car while no car exists")
        void editCar_CarListEmpty() {
            when(carList.isEmpty()).thenReturn(true);
            when(carList.containsKey(anyInt())).thenReturn(false);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.NOT_FOUND, carController.addCarProperties(emptyCar).getStatusCode());

        }

        @Test
        @DisplayName("Edit a car without providing an ID")
        void editCar_withoutID() {
            //change mock car
            when(emptyCar.getId()).thenReturn(null);

            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(true);
            when(carDataHandler.getCarList()).thenReturn(carList);

            assertEquals(HttpStatus.BAD_REQUEST, carController.addCarProperties(emptyCar).getStatusCode());
        }
    }


    @Nested
    @DisplayName("Every option to delete a car")
    class deleteCar {

        HashMap<Integer, Car> carList = Mockito.mock(HashMap.class);

        @BeforeEach
        void setupMockCar(){
            when(emptyCar.getId()).thenReturn(7);
        }

        @Test
        @DisplayName("Delete a car successfully")
        void deleteCar() {
            //setup Mock Hashmap to avoid error in deleteCar function from Controller
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(true);

            //successfully deleted
            when(carDataHandler.getCarList()).thenReturn(carList);
            assertEquals(HttpStatus.NO_CONTENT, carController.deleteCarJSON(emptyCar).getStatusCode());
        }

        @Test
        @DisplayName("Delete a car with an Invalid ID")
        void deleteCar_InvalidID() {
            when(carList.isEmpty()).thenReturn(false);
            when(carList.containsKey(anyInt())).thenReturn(false);

            when(carDataHandler.getCarList()).thenReturn(carList);
            assertEquals(HttpStatus.NOT_FOUND, carController.deleteCarJSON(emptyCar).getStatusCode());
        }

        @Test
        @DisplayName("Delete a car while no car exists")
        void deleteCar_NoCarToDelete() {
            when(carList.isEmpty()).thenReturn(true);
            when(carList.containsKey(anyInt())).thenReturn(false);

            when(carDataHandler.getCarList()).thenReturn(carList);
            assertEquals(HttpStatus.NOT_FOUND, carController.deleteCarJSON(emptyCar).getStatusCode());
        }
    }
}