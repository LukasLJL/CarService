package com.nttdata.CarService.controller;

import com.nttdata.CarService.entity.Car;
import com.nttdata.CarService.handler.CarDataHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/car")
public class CarController {

    @PostMapping("/create")
    public ResponseEntity<String> createCar(@RequestParam String marke, @RequestParam String modell,
                                            @RequestParam Integer gewicht, @RequestParam Integer leistung, @RequestParam String farbe,
                                            @RequestParam(required = false) String klasse,
                                            @RequestParam(required = false) Integer tueren,
                                            @RequestParam(required = false) Integer drehmoment,
                                            @RequestParam(required = false) String motor_art) {

        int id = CarDataHandler.createCar(marke, modell, gewicht, leistung, farbe, klasse, tueren, drehmoment, motor_art);
        return new ResponseEntity<>("Created Car with ID: " + id, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public @ResponseBody
    HashMap<Integer, Car> listCar() {
        return CarDataHandler.getCarList();
    }

    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Car listSelectedCar(@PathVariable int id) {
        return CarDataHandler.getCarList().get(id);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> addCarProperties(@RequestParam Integer id, @RequestParam(required = false) String marke,
                                                   @RequestParam(required = false) String modell, @RequestParam(required = false) Integer gewicht,
                                                   @RequestParam(required = false) Integer leistung, @RequestParam(required = false) Integer drehmoment,
                                                   @RequestParam(required = false) String farbe, @RequestParam(required = false) Integer tueren,
                                                   @RequestParam(required = false) String klasse, @RequestParam(required = false) String motor_art) {
        if (id == null) {
            return new ResponseEntity<>("No ID", HttpStatus.BAD_REQUEST);
        }
        if (CarDataHandler.getCarList().isEmpty()) {
            return new ResponseEntity<>("No Cars", HttpStatus.NOT_FOUND);
        }
        if (!CarDataHandler.getCarList().containsKey(id)) {
            return new ResponseEntity<>("No Car with this id", HttpStatus.NOT_FOUND);
        }
        if (id != null && !CarDataHandler.getCarList().isEmpty() && CarDataHandler.getCarList().containsKey(id)) {
            CarDataHandler.editCar(id, marke, modell, gewicht, leistung, drehmoment, farbe, tueren, klasse, motor_art);
        }
        return new ResponseEntity<>("Properties added!", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCar(@RequestParam int id) {
        if (CarDataHandler.getCarList().isEmpty()) {
            return new ResponseEntity<>("No Cars", HttpStatus.NOT_FOUND);
        } else if (!CarDataHandler.getCarList().containsKey(id)) {
            return new ResponseEntity<>("Id is invalid", HttpStatus.NOT_FOUND);
        }
        CarDataHandler.deleteCar(id);
        return new ResponseEntity<>("Removed!", HttpStatus.NO_CONTENT);
    }

}
