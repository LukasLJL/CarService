package com.nttdata.CarService.controller;

import com.nttdata.CarService.entity.Car;
import com.nttdata.CarService.handler.CarDataHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/car")
public class CarController {

    //POST create Car with parameters
    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> createCar(@RequestParam String marke, @RequestParam String modell,
                                            @RequestParam Integer gewicht, @RequestParam Integer leistung, @RequestParam String farbe,
                                            @RequestParam(required = false) String klasse,
                                            @RequestParam(required = false) Integer tueren,
                                            @RequestParam(required = false) Integer drehmoment,
                                            @RequestParam(required = false) String motor_art) {

        int id = CarDataHandler.createCar(marke, modell, gewicht, leistung, farbe, klasse, tueren, drehmoment, motor_art);
        return new ResponseEntity<>("Created Car with ID: " + id, HttpStatus.CREATED);
    }

    //POST create Car with json
    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCar (@RequestBody Car car){
        CarDataHandler.createCar(car);
        return new ResponseEntity<>("Created Car with ID: " +car.getId(), HttpStatus.CREATED);
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

    //PUT edit Car with parameters
    @RequestMapping (method = RequestMethod.PUT, value = "/edit", consumes = APPLICATION_FORM_URLENCODED_VALUE)
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

    //PUT edit Car with json
    @RequestMapping(method = RequestMethod.PUT, value = "/edit", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCarProperties(@RequestBody Car car) {
        if (car.getId() == null) {
            return new ResponseEntity<>("No ID", HttpStatus.BAD_REQUEST);
        }
        if (CarDataHandler.getCarList().isEmpty()) {
            return new ResponseEntity<>("No Cars", HttpStatus.NOT_FOUND);
        }
        if (!CarDataHandler.getCarList().containsKey(car.getId())) {
            return new ResponseEntity<>("No Car with this id", HttpStatus.NOT_FOUND);
        }
        if (car.getId() != null && !CarDataHandler.getCarList().isEmpty() && CarDataHandler.getCarList().containsKey(car.getId())) {
            CarDataHandler.editCar(car);
        }
        return new ResponseEntity<>("Properties added!", HttpStatus.OK);
    }

    //DELETE Car with parameter id
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> deleteCar(@RequestParam int id) {
        if (CarDataHandler.getCarList().isEmpty()) {
            return new ResponseEntity<>("No Cars", HttpStatus.NOT_FOUND);
        } else if (!CarDataHandler.getCarList().containsKey(id)) {
            return new ResponseEntity<>("Id is invalid", HttpStatus.NOT_FOUND);
        }
        CarDataHandler.deleteCar(id);
        return new ResponseEntity<>("Removed!", HttpStatus.NO_CONTENT);
    }

    //DELETE Car with json
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCarJSON(@RequestBody Car car) {
        if (CarDataHandler.getCarList().isEmpty()) {
            return new ResponseEntity<>("No Cars", HttpStatus.NOT_FOUND);
        } else if (!CarDataHandler.getCarList().containsKey(car.getId())) {
            return new ResponseEntity<>("Id is invalid", HttpStatus.NOT_FOUND);
        }
        CarDataHandler.deleteCar(car.getId());
        return new ResponseEntity<>("Removed!", HttpStatus.NO_CONTENT);
    }
}
