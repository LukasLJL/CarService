package com.nttdata.CarService.handler;

import com.nttdata.CarService.entity.Car;
import com.nttdata.CarService.repository.CarRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * DataHandler for the REST API <br>
 * Managing how the data has to been stored for the API <br>
 *
 * @author Lukas
 */
@Component
public class CarDataHandler {


    private static final Logger LOGGER = LogManager.getLogger(CarDataHandler.class);

    private static int id;

    final CarRepository carRepository;

    public CarDataHandler(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    /**
     * create car <br>
     * car will be stored in the 'database'<br>
     *
     * @param car car object
     * @return car
     * @since 1.1
     */
    //create Car with JSON
    public Car createCar(Car car) {
        if (car.getId() != null && !carRepository.existsById(car.getId())) {
            car.setId(car.getId());
        } else {
            car.setId(id++);
            while (carRepository.existsById(car.getId())) {
                car.setId(id++);
            }
        }
        carRepository.save(car);
        LOGGER.info("DATA | Created Car with ID:" + car.getId());
        return car;
    }

    /**
     * edit properties of a car
     * ID is required
     *
     * @param car car object
     * @return car
     * @since 1.1
     */

    //edit Car with JSON
    public Car editCar(Car car) {
        String newContent = "";
        if (car.getId() == null || !carRepository.existsById(car.getId())) {
            LOGGER.error("DATA | No Car ID!");
            return null;
        }

        Car updatedCar = carRepository.findById(car.getId()).get();

        if (car.getMarke() != null) {
            updatedCar.setMarke(car.getMarke());
            newContent += "marke: " + car.getMarke() + " // ";
        }
        if (car.getModel() != null) {
            updatedCar.setModel(car.getModel());
            newContent += "model: " + car.getModel() + " // ";
        }
        if (car.getGewicht() != 0) {
            updatedCar.setGewicht(car.getGewicht());
            newContent += "gewicht: " + car.getGewicht() + " // ";
        }
        if (car.getLeistung() != 0) {
            updatedCar.setLeistung(car.getLeistung());
            newContent += "leistung: " + car.getLeistung() + " // ";
        }
        if (car.getDrehmoment() != 0) {
            updatedCar.setDrehmoment(car.getDrehmoment());
            newContent += "drehmoment: " + car.getDrehmoment() + " // ";
        }
        if (car.getFarbe() != null) {
            updatedCar.setFarbe(car.getFarbe());
            newContent += "farbe: " + car.getFarbe() + " // ";
        }
        if (car.getTueren() != 0) {
            updatedCar.setTueren(car.getTueren());
            newContent += "türen: " + car.getTueren() + " // ";
        }
        if (car.getKlasse() != null) {
            updatedCar.setKlasse(car.getKlasse());
            newContent += "klasse: " + car.getKlasse() + " // ";
        }
        if (car.getMotor_art() != null) {
            updatedCar.setMotor_art(car.getMotor_art());
            newContent += "motor-art: " + car.getMotor_art();
        }

        carRepository.save(updatedCar);
        LOGGER.info("DATA | Changed properties of car with ID: " + updatedCar.getId() + " // " + newContent);
        return updatedCar;
    }

    /**
     * delete a car from the 'database'<br>
     *
     * @param id car id to delete car
     */
    public void deleteCar(Integer id) {
        Car deleteCar = new Car();
        deleteCar.setId(id);
        carRepository.delete(deleteCar);
        LOGGER.info("DATA | Removed Car with ID: " + id);
    }

    public HashMap<Integer, Car> getCarList() {
        LOGGER.debug("DATA | List Cars");
        ArrayList<Car> carArrayList = new ArrayList<>();
        Iterator<Car> carIterator = carRepository.findAll().iterator();
        while (carIterator.hasNext()) {
            carArrayList.add(carIterator.next());
        }
        HashMap<Integer, Car> tempCarList = new HashMap<>();
        Iterator<Car> carIterator1 = carArrayList.iterator();
        while (carIterator1.hasNext()) {
            Car tempCar = carIterator1.next();
            tempCarList.put(tempCar.getId(), tempCar);
        }
        return tempCarList;
    }

    public void resetData(){
        LOGGER.info("DATA | RESET DATA");
        id = 0;
        carRepository.deleteAll();
    }


    /**
     * --- LEGACY --- <br>
     * create car <br>
     * car will be stored in the 'database'<br>
     *
     * @param marke      - brand
     * @param model      - model
     * @param gewicht    - weight
     * @param leistung   - power
     * @param drehmoment - torque
     * @param farbe      - color
     * @param tueren     - number of doors
     * @param klasse     - car typ (cabrio, sport, coupe)
     * @param motor_art  - engine typ (diesel, gasoline, electric)
     * @return car
     * @since 1.0
     * @deprecated
     */
    @Deprecated
    public Car createCar(String marke, String model,
                         Integer gewicht, Integer leistung, String farbe,
                         String klasse, Integer tueren, Integer drehmoment, String motor_art) {
        Car car = new Car();
        car.setId(id++);
        car.setMarke(marke);
        car.setModel(model);
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
        carRepository.save(car);
        LOGGER.info("DATA | Created Car with ID:" + car.getId());

        return car;
    }

    /**
     * --- LEGACY ---<br>
     * edit properties of a car
     *
     * @param id         - id (required)
     * @param marke      - brand
     * @param model      - model
     * @param gewicht    - weight
     * @param leistung   - power
     * @param drehmoment - torque
     * @param farbe      - color
     * @param tueren     - number of doors
     * @param klasse     - car typ (cabrio, sport, coupe)
     * @param motor_art  - engine typ (diesel, gasoline, electric)
     * @since 1.0
     * @deprecated
     */
    @Deprecated
    public void editCar(Integer id, String marke, String model, Integer gewicht,
                        Integer leistung, Integer drehmoment, String farbe,
                        Integer tueren, String klasse, String motor_art) {
        String newContent = "";

        if (id == null || !carRepository.existsById(id)) {
            LOGGER.error("DATA | No Car ID!");
            return;
        }
        if (marke != null) {
            carRepository.findById(id).get().setMarke(marke);
            newContent += "marke: " + marke + " // ";
        }
        if (model != null) {
            carRepository.findById(id).get().setModel(model);
            newContent += "model: " + model + " // ";
        }
        if (gewicht != null && gewicht != 0) {
            carRepository.findById(id).get().setGewicht(gewicht);
            newContent += "gewicht: " + gewicht + " // ";
        }
        if (leistung != null && leistung != 0) {
            carRepository.findById(id).get().setLeistung(leistung);
            newContent += "leistung: " + leistung + " // ";
        }
        if (drehmoment != null && drehmoment != 0) {
            carRepository.findById(id).get().setDrehmoment(drehmoment);
            newContent += "drehmoment: " + drehmoment + " // ";
        }
        if (farbe != null) {
            carRepository.findById(id).get().setFarbe(farbe);
            newContent += "farbe: " + farbe + " // ";
        }
        if (tueren != null && tueren != 0) {
            carRepository.findById(id).get().setTueren(tueren);
            newContent += "türen: " + tueren + " // ";
        }
        if (klasse != null) {
            carRepository.findById(id).get().setKlasse(klasse);
            newContent += "klasse: " + klasse + " // ";
        }
        if (motor_art != null) {
            carRepository.findById(id).get().setMotor_art(motor_art);
            newContent += "motor-art: " + motor_art;
        }
        LOGGER.info("DATA | Changed properties of car with ID: " + carRepository.findById(id).get().getId() + " // " + newContent);
    }
}
