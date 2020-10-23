package com.nttdata.CarService.handler;

import com.nttdata.CarService.entity.Car;
import com.nttdata.CarService.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


public class CarDataHandlerTest {


    private CarDataHandler unitTestData;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    public void init() {
        carRepository = Mockito.mock(CarRepository.class);
        unitTestData = new CarDataHandler(carRepository);
    }

    @Nested
    @DisplayName("Create test Cars")
    class createCar {

        Car car;

        @BeforeEach
        @DisplayName("Init to create cars")
        void initCar(){
            car = new Car();
            car.setMarke("Mercedes");
            car.setModel("CL 500");
            car.setLeistung(380);

            when(carRepository.existsById(anyInt())).thenReturn(false);
        }

        @Test
        @DisplayName("Create a car successfully")
        void createCar_normal() {
            final Car result = unitTestData.createCar(car);
            assertEquals(car, result);
        }

        @Test
        @DisplayName("Create a car with a given ID")
        void createCarWithGivenID() {
            car.setId(25);

            Car result = unitTestData.createCar(car);
            assertEquals(car.getId(), result.getId());
        }

        @Test
        @DisplayName("Create a car with a given which is already taken")
        void createCarWithGivenIDWhichIsTaken() {
            Car result = unitTestData.createCar(car);

            Car car2 = new Car();
            car2.setId(0);
            car2.setMarke("Mercedes");
            car2.setModel("CL");
            car2.setLeistung(380);

            when(carRepository.existsById(anyInt())).thenReturn(true, false);

            Car result2 = unitTestData.createCar(car2);

            assertNotEquals(result.getId(), result2.getId());
        }
    }


    @Nested
    @DisplayName("Every option to edit a car")
    class editCar {

        Car car;
        Car saveCar;

        @BeforeEach
        @DisplayName("Init a car")
        void initCar(){
            car = new Car();
            car.setMarke("VW");
            car.setModel("Golf");
            car.setLeistung(100);
            car.setFarbe("schwarz");

            when(carRepository.existsById(anyInt())).thenReturn(false);
            when(carRepository.findById(anyInt())).thenReturn(Optional.of(car));
            unitTestData.createCar(car);
        }

        @Test
        @DisplayName("Edit a Car successfully")
        void editCar_normal(){
            //save color from original car
            final String color1 = car.getFarbe();

            //edit the color to "silber"
            Car updatedCar = new Car();
            updatedCar.setId(car.getId());
            updatedCar.setMarke("VW");
            updatedCar.setModel("Golf");
            updatedCar.setLeistung(100);
            updatedCar.setFarbe("silber");

            //Mock settings
            when(carRepository.existsById(anyInt())).thenReturn(true);

            Car resultCar = unitTestData.editCar(updatedCar);

            final String color2 = resultCar.getFarbe();
            assertNotEquals(color1, color2);
        }

        @Test
        @DisplayName("Edit a Car with an Invalid ID")
        void editCar_InvalidID() {
            Car updateCar = new Car();
            updateCar.setId(-1);

            //Mock settings
            when(carRepository.existsById(anyInt())).thenReturn(false);

            Car resultCar = unitTestData.editCar(updateCar);

            assertNull(resultCar);
        }

        @Test
        @DisplayName("Edit a Car without select an ID")
        void editCar_ContainsNoID() {
            Car updateCar = new Car();

            //Mock settings
            Car resultCar = unitTestData.editCar(updateCar);

            assertNull(resultCar);
        }

    }

    @Test
    @DisplayName("Delete a car")
    void deleteCar() {
        Car car = new Car();
        car.setMarke("Audi");
        car.setModel("A4");
        car.setGewicht(1400);

        when(carRepository.existsById(anyInt())).thenReturn(false);

        unitTestData.createCar(car);
        unitTestData.deleteCar(car.getId());

        assertFalse(unitTestData.getCarList().containsKey(car.getId()));
    }
}