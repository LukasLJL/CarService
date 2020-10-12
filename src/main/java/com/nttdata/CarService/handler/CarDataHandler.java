package com.nttdata.CarService.handler;

import com.nttdata.CarService.entity.Car;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CarDataHandler {

    static HashMap<Integer, Car> carList = new HashMap<>();
    private static final Logger LOGGER = LogManager.getLogger(CarDataHandler.class);


    private static int id;

    public Car createCar(String marke, String modell,
                         Integer gewicht, Integer leistung, String farbe,
                         String klasse, Integer tueren, Integer drehmoment, String motor_art) {
        Car car = new Car();
        car.setId(id++);
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
        LOGGER.info("DATA | Created Car with ID:" + car.getId());

        return car;
    }

    //create Car with JSON
    public Car createCar(Car car) {
        if (car.getId() != null && !carList.containsKey(car.getId())) {
            car.setId(car.getId());
        } else {
            car.setId(id++);
        }
        carList.put(car.getId(), car);
        LOGGER.info("DATA | Created Car with ID:" + car.getId());
        return car;
    }

    public void editCar(Integer id, String marke, String modell, Integer gewicht,
                        Integer leistung, Integer drehmoment, String farbe,
                        Integer tueren, String klasse, String motor_art) {
        String newContent = "";

        if (id == null || !carList.containsKey(id)) {
            LOGGER.error("DATA | No Car ID!");
            return;
        }

        if (marke != null) {
            carList.get(id).setMarke(marke);
            newContent += "marke: " + marke + " // ";
        }
        if (modell != null) {
            carList.get(id).setModell(modell);
            newContent += "modell: " + modell + " // ";
        }
        if (gewicht != null && gewicht != 0) {
            carList.get(id).setGewicht(gewicht);
            newContent += "gewicht: " + gewicht + " // ";
        }
        if (leistung != null && leistung != 0) {
            carList.get(id).setLeistung(leistung);
            newContent += "leistung: " + leistung + " // ";
        }
        if (drehmoment != null && drehmoment != 0) {
            carList.get(id).setDrehmoment(drehmoment);
            newContent += "drehmoment: " + drehmoment + " // ";
        }
        if (farbe != null) {
            carList.get(id).setFarbe(farbe);
            newContent += "farbe: " + farbe + " // ";
        }
        if (tueren != null && tueren != 0) {
            carList.get(id).setTueren(tueren);
            newContent += "türen: " + tueren + " // ";
        }
        if (klasse != null) {
            carList.get(id).setKlasse(klasse);
            newContent += "klasse: " + klasse + " // ";
        }
        if (motor_art != null) {
            carList.get(id).setMotor_art(motor_art);
            newContent += "motor-art: " + motor_art;
        }
        LOGGER.info("DATA | Changed properties of car with ID: " + carList.get(id).getId() + " // " + newContent);
    }

    //edit Car with JSON
    public Car editCar(Car car) {
        String newContent = "";
        if (car.getId() == null || !carList.containsKey(car.getId())) {
            LOGGER.error("DATA | No Car ID!");
            return null;
        }
        if (car.getMarke() != null) {
            carList.get(car.getId()).setMarke(car.getMarke());
            newContent += "marke: " + car.getMarke() + " // ";
        }
        if (car.getModell() != null) {
            carList.get(car.getId()).setModell(car.getModell());
            newContent += "modell: " + car.getModell() + " // ";
        }
        if (car.getGewicht() != 0) {
            carList.get(car.getId()).setGewicht(car.getGewicht());
            newContent += "gewicht: " + car.getGewicht() + " // ";
        }
        if (car.getLeistung() != 0) {
            carList.get(car.getId()).setLeistung(car.getLeistung());
            newContent += "leistung: " + car.getLeistung() + " // ";
        }
        if (car.getDrehmoment() != 0) {
            carList.get(car.getId()).setDrehmoment(car.getDrehmoment());
            newContent += "drehmoment: " + car.getDrehmoment() + " // ";
        }
        if (car.getFarbe() != null) {
            carList.get(car.getId()).setFarbe(car.getFarbe());
            newContent += "farbe: " + car.getFarbe() + " // ";
        }
        if (car.getTueren() != 0) {
            carList.get(car.getId()).setTueren(car.getTueren());
            newContent += "türen: " + car.getTueren() + " // ";
        }
        if (car.getKlasse() != null) {
            carList.get(car.getId()).setKlasse(car.getKlasse());
            newContent += "klasse: " + car.getKlasse() + " // ";
        }
        if (car.getMotor_art() != null) {
            carList.get(car.getId()).setMotor_art(car.getMotor_art());
            newContent += "motor-art: " + car.getMotor_art();
        }
        LOGGER.info("DATA | Changed properties of car with ID: " + carList.get(car.getId()).getId() + " // " + newContent);
        return car;
    }

    public void deleteCar(Integer id) {
        carList.remove(id);
        LOGGER.info("DATA | Removed Car with ID: " + id);
    }

    public HashMap<Integer, Car> getCarList() {
        LOGGER.debug("DATA | List Cars");
        return carList;
    }

}
