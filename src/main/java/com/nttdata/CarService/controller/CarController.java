package com.nttdata.CarService.controller;


import com.nttdata.CarService.entity.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/car")
public class CarController {

    ArrayList<Car> carList = new ArrayList<Car>();

    @PostMapping("/create")
    public ResponseEntity<String> createCar(@RequestParam String marke, @RequestParam String modell,
                                            @RequestParam int gewicht, @RequestParam int leistung, @RequestParam String farbe,
                                            @RequestParam(required = false) String klasse,
                                            @RequestParam(required = false) Integer tueren,
                                            @RequestParam(required = false) Integer drehmoment,
                                            @RequestParam(required = false) String motor_art) {
        Car car = new Car();
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
        carList.add(car);
        return new ResponseEntity<String>("Created Car!", HttpStatus.OK);
    }

    @GetMapping("/list")
    public @ResponseBody
    ArrayList<Car> listCar() {
        return carList;
    }

    @GetMapping("/list/{id}")
    public @ResponseBody
    Car listSelectedCar(@PathVariable int id) {
        return carList.get(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> addCarProperties(@RequestParam int id, @RequestParam(required = false) String marke,
                                                   @RequestParam(required = false) String modell, @RequestParam(required = false) Integer gewicht,
                                                   @RequestParam(required = false) Integer leistung, @RequestParam(required = false) Integer drehmoment,
                                                   @RequestParam(required = false) String farbe, @RequestParam(required = false) Integer tueren,
                                                   @RequestParam(required = false) String klasse, @RequestParam(required = false) String motor_art) {
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
        return new ResponseEntity<String>("Properties added!", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCar(@RequestParam int id) {
        carList.remove(id);
        return new ResponseEntity<String>("Removed!", HttpStatus.OK);
    }

}
