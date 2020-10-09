package com.nttdata.CarService.handler;

import com.nttdata.CarService.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarDataHandlerTest {


    private CarDataHandler unitTestData;

    @BeforeEach
    public void init() {
        unitTestData = new CarDataHandler();
    }

    @Test
    void createCar() {
        Car car = new Car();
        car.setMarke("Mercedes");
        car.setModell("CL");
        car.setLeistung(380);
        final Car result = unitTestData.createCar(car);
        assertEquals(car, result);
    }

    @Test
    void createCarWithGivenID() {
        Car car = new Car();
        car.setId(25);
        car.setMarke("Mercedes");
        car.setModell("CL");
        car.setLeistung(380);
        Car result = unitTestData.createCar(car);
        assertEquals(car.getId(), result.getId());
    }

    @Test
    void createCarWithGivenIDWhichIsTaken() {
        Car car = new Car();
        car.setMarke("Mercedes");
        car.setModell("CL");
        car.setLeistung(380);
        Car result = unitTestData.createCar(car);

        Car car2 = new Car();
        car2.setId(0);
        car2.setMarke("Mercedes");
        car2.setModell("CL");
        car2.setLeistung(380);
        Car result2 = unitTestData.createCar(car2);

        assertNotEquals(result.getId(), result2.getId());
    }


    @Test
    void editCar() {
        Car car = new Car();
        car.setMarke("VW");
        car.setModell("Golf");
        car.setLeistung(100);
        car.setFarbe("schwarz");
        unitTestData.createCar(car);
        final String farbe1 = unitTestData.getCarList().get(car.getId()).getFarbe();

        Car updatedCar = new Car();
        updatedCar.setId(unitTestData.getCarList().get(car.getId()).getId());
        updatedCar.setMarke("VW");
        updatedCar.setModell("Golf");
        updatedCar.setLeistung(100);
        updatedCar.setFarbe("silber");
        unitTestData.editCar(updatedCar);
        final String farbe2 = unitTestData.getCarList().get(car.getId()).getFarbe();
        assertNotEquals(farbe1, farbe2);
    }

    @Test
    void editCar_InvalidID() {
        Car car = new Car();
        car.setMarke("VW");
        car.setModell("Golf");
        car.setLeistung(100);
        car.setFarbe("schwarz");
        unitTestData.createCar(car);

        Car updateCar = new Car();
        updateCar.setId(-1);
        Car result = unitTestData.editCar(updateCar);
        assertNull(result);
    }

    @Test
    void editCar_ContainsNoID(){
        Car car = new Car();
        car.setMarke("VW");
        car.setModell("Golf");
        car.setLeistung(100);
        car.setFarbe("schwarz");
        unitTestData.createCar(car);

        Car updateCar = new Car();
        Car result = unitTestData.editCar(updateCar);
        assertNull(result);
    }
    @Test
    void deleteCar() {
        Car car = new Car();
        car.setMarke("Audi");
        car.setModell("A4");
        car.setGewicht(1400);
        unitTestData.createCar(car);
        unitTestData.deleteCar(car.getId());
        assertFalse(unitTestData.getCarList().containsKey(car.getId()));
    }

}