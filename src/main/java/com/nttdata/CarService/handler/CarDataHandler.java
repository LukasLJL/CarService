package com.nttdata.CarService.handler;

import com.nttdata.CarService.entity.Car;

import java.util.HashMap;

public class CarDataHandler {

    static HashMap<Integer, Car> carList = new HashMap<Integer, Car>();

    private static int id;

    public static int createCar(String marke, String modell,
                                 Integer gewicht, Integer leistung, String farbe,
                                 String klasse, Integer tueren, Integer drehmoment, String motor_art) {
        Car car = new Car();
        car.setId(id ++);
        car.setMarke(marke);
        car.setModell(modell);
        car.setGewicht(gewicht);
        car.setLeistung(leistung);
        car.setFarbe(farbe);
        if (drehmoment != null) {
            car.setDrehmoment(drehmoment);
        }
        if (klasse != null) {
            car.setKlasse(klasse);
        }
        if (tueren != null) {
            car.setTueren(tueren);
        }
        if (motor_art != null) {
            car.setMotor_art(motor_art);
        }
        carList.put(car.getId(), car);
        return car.getId();
    }

    //create Car with JSON
    public static int createCar(Car car) {
        car.setId(id ++);
        carList.put(car.getId(), car);
        return car.getId();
    }

    public static void editCar(Integer id, String marke, String modell, Integer gewicht,
                               Integer leistung, Integer drehmoment, String farbe,
                               Integer tueren, String klasse, String motor_art) {

        if (marke != null) {
            carList.get(id).setMarke(marke);
        }
        if (modell != null) {
            carList.get(id).setModell(modell);
        }
        if (gewicht != null && gewicht != 0) {
            carList.get(id).setGewicht(gewicht);
        }
        if (leistung != null && leistung != 0) {
            carList.get(id).setLeistung(leistung);
        }
        if (drehmoment != null && drehmoment != 0) {
            carList.get(id).setDrehmoment(drehmoment);
        }
        if (farbe != null) {
            carList.get(id).setFarbe(farbe);
        }
        if (tueren != null && tueren != 0) {
            carList.get(id).setTueren(tueren);
        }
        if (klasse != null) {
            carList.get(id).setKlasse(klasse);
        }
        if (motor_art != null) {
            carList.get(id).setMotor_art(motor_art);
        }
    }

    //edit Car with JSON
    public static void editCar(Car car) {
        if (car.getMarke() != null) {
            carList.get(car.getId()).setMarke(car.getMarke());
        }
        if (car.getModell() != null) {
            carList.get(car.getId()).setModell(car.getModell());
        }
        if (car.getGewicht() != 0) {
            carList.get(car.getId()).setGewicht(car.getGewicht());
        }
        if ( car.getLeistung() != 0) {
            carList.get(car.getId()).setLeistung(car.getLeistung());
        }
        if (car.getDrehmoment() != 0) {
            carList.get(car.getId()).setDrehmoment(car.getDrehmoment());
        }
        if (car.getFarbe() != null) {
            carList.get(car.getId()).setFarbe(car.getFarbe());
        }
        if (car.getTueren() != 0) {
            carList.get(car.getId()).setTueren(car.getTueren());
        }
        if (car.getKlasse() != null) {
            carList.get(car.getId()).setKlasse(car.getKlasse());
        }
        if (car.getMotor_art() != null) {
            carList.get(car.getId()).setMotor_art(car.getMotor_art());
        }
    }

    public static void deleteCar(Integer id) {
        carList.remove(id);
    }

    public static HashMap<Integer, Car> getCarList() {
        return carList;
    }

    public static void setCarList(HashMap<Integer, Car> carList) {
        CarDataHandler.carList = carList;
    }


}
