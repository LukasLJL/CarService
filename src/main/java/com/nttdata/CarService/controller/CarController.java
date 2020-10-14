package com.nttdata.CarService.controller;

import com.nttdata.CarService.entity.Car;
import com.nttdata.CarService.handler.CarDataHandler;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static org.springframework.http.MediaType.*;

/**
 * Car Controller
 * <p>
 * Handling all the CRUD REST Requests <br>
 * Main controller for the API <br>
 *
 * @author Lukas
 */
@Controller
@RequestMapping("/car")
public class CarController {

    private final CarDataHandler carDataHandler;
    private static final Logger LOGGER = LogManager.getLogger(CarController.class);

    @Autowired
    public CarController(CarDataHandler carDataHandler) {
        this.carDataHandler = carDataHandler;
    }

    /*
    JSON INPUT FOR API
     */

    /**
     * POST Request | Create Car with JSON <br>
     *
     * @param car car Object
     * @return ResponseEntity
     */
    @ApiOperation(value = "Create a Car", notes = "Provide a JSON Car Object to create a new car")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Created Car")
            }
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCar(@RequestBody Car car) {
        LOGGER.debug("POST Request | JSON Object");
        carDataHandler.createCar(car);
        LOGGER.info("POST Request | Successfully added JSON Car object");
        return new ResponseEntity<>("Created Car with ID: " + car.getId(), HttpStatus.CREATED);
    }

    /**
     * GET Request | List all cars <br>
     *
     * @return carList
     */
    @ApiOperation(value = "List all Cars", notes = "List all Cars as a json")
    @GetMapping("/list")
    public @ResponseBody
    HashMap<Integer, Car> listCar() {
        LOGGER.debug("GET Request | List all cars");
        return carDataHandler.getCarList();
    }

    /**
     * GET Request | List selected car <br>
     *
     * @param id car id to select car
     * @return car
     */
    @ApiOperation(value = "List selected car", notes = "List selected car as a json")
    @GetMapping("/list/{id}")
    public @ResponseBody
    Car listSelectedCar(@PathVariable int id) {
        LOGGER.debug("GET Request | List selected car");
        return carDataHandler.getCarList().get(id);
    }

    /**
     * PUT Request | Edit car with JSON <br>
     * ID is required to change or add properties of the car <br>
     *
     * @param car car object
     * @return ResponseEntity
     */
    @ApiOperation(value = "Add or change properties of a car", notes = "Provide a valid ID to change or add properties to an exiting car")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "No ID"),
                    @ApiResponse(code = 404, message = "Invalid ID / No Car found with ID"),
                    @ApiResponse(code = 200, message = "Properties added!")
            }
    )
    @RequestMapping(method = RequestMethod.PUT, value = "/edit", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCarProperties(@RequestBody Car car) {
        LOGGER.debug("PUT Request | Change or add properties of a car");

        if (car.getId() == null) {
            LOGGER.error("PUT Request | No ID selected");
            return new ResponseEntity<>("No ID", HttpStatus.BAD_REQUEST);
        }
        if (carDataHandler.getCarList().isEmpty()) {
            LOGGER.error("PUT Request | No Cars");
            return new ResponseEntity<>("No Cars", HttpStatus.NOT_FOUND);
        }
        if (!carDataHandler.getCarList().containsKey(car.getId())) {
            LOGGER.error("PUT Request | Invalid ID / No Car found with ID:" + car.getId());
            return new ResponseEntity<>("No Car with this id", HttpStatus.NOT_FOUND);
        }
        if (car.getId() != null && !carDataHandler.getCarList().isEmpty() && carDataHandler.getCarList().containsKey(car.getId())) {
            carDataHandler.editCar(car);
            LOGGER.info("PUT Request | Successfully added JSON car");
        }
        return new ResponseEntity<>("Properties added!", HttpStatus.OK);
    }

    /**
     * DELETE Request | Delete Car with id parameter <br>
     *
     * @param id car id to delete selected car
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "Delete Car", notes = "Delete a selected Car with an ID", hidden = true)
    public ResponseEntity<String> deleteCar(@RequestParam int id) {
        LOGGER.debug("DELETE Request | delete selected car");

        if (carDataHandler.getCarList().isEmpty()) {
            LOGGER.error("DELETE Request | No Cars");
            return new ResponseEntity<>("No Cars", HttpStatus.NOT_FOUND);
        } else if (!carDataHandler.getCarList().containsKey(id)) {
            LOGGER.error("DELETE Request | Invalid ID / No Car found with ID: " + id);
            return new ResponseEntity<>("Id is invalid", HttpStatus.NOT_FOUND);
        }

        carDataHandler.deleteCar(id);

        LOGGER.info("DELETE Request | Deleted Car with parameter");
        return new ResponseEntity<>("Removed!", HttpStatus.NO_CONTENT);
    }

    /**
     * DELETE Request | Delete a car with JSON <br>
     * Only ID is required <br>
     *
     * @param car car object
     * @return ResponseEntity
     */
    @ApiOperation(value = "Delete Car", notes = "Delete a selected Car with an ID")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 204, message = "Removed!"),
                    @ApiResponse(code = 404, message = "Invalid ID / No Car found with ID"),
            }
    )
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCarJSON(@RequestBody Car car) {
        LOGGER.debug("DELETE Request | delete selected car");
        if (carDataHandler.getCarList().isEmpty()) {
            LOGGER.error("DELETE Request | No Cars");
            return new ResponseEntity<>("No Cars", HttpStatus.NOT_FOUND);
        } else if (!carDataHandler.getCarList().containsKey(car.getId())) {
            LOGGER.error("DELETE Request | Invalid ID / No Car found with ID: " + car.getId());
            return new ResponseEntity<>("Id is invalid", HttpStatus.NOT_FOUND);
        }
        carDataHandler.deleteCar(car.getId());
        LOGGER.info("DELETE Request | Deleted JSON car");
        return new ResponseEntity<>("Removed!", HttpStatus.NO_CONTENT);
    }


    /*
    LEGACY // USE API WITH PARAMETER
    OUTDATED
    OUTDATED
     */

    /**
     * POST Request | Create car with parameter <br>
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
     * @return ResponseEntity
     * @since 1.0
     * @deprecated
     */
    @Deprecated
    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    @ApiOperation(value = "Create a Car", hidden = true)
    public ResponseEntity<String> createCar(@RequestParam String marke, @RequestParam String model,
                                            @RequestParam Integer gewicht, @RequestParam Integer leistung, @RequestParam String farbe,
                                            @RequestParam(required = false) String klasse,
                                            @RequestParam(required = false) Integer tueren,
                                            @RequestParam(required = false) Integer drehmoment,
                                            @RequestParam(required = false) String motor_art) {
        LOGGER.debug("POST Request | with parameter");

        Car newCar = carDataHandler.createCar(marke, model, gewicht, leistung, farbe, klasse, tueren, drehmoment, motor_art);
        LOGGER.info("POST Request | Successfully added with parameter");
        return new ResponseEntity<>("Created Car with ID: " + newCar.getId(), HttpStatus.CREATED);
    }

    /**
     * PUT Request | Edit car with parameter <br>
     * ID is required to change or add properties of the car <br>
     *
     * @param id         - ID
     * @param marke      - brand
     * @param model      - model
     * @param gewicht    - weight
     * @param leistung   - power
     * @param drehmoment - torque
     * @param farbe      - color
     * @param tueren     - number of doors
     * @param klasse     - car typ (cabrio, sport, coupe)
     * @param motor_art  - engine typ (diesel, gasoline, electric)
     * @return ResponseEntity
     * @since 1.0
     * @deprecated
     */
    @Deprecated
    @ApiOperation(value = "Add or change properties of a car (parameter)", notes = "Provide a valid ID to change or add properties to an exiting car", hidden = true)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "No ID"),
                    @ApiResponse(code = 404, message = "Invalid ID / No Car found with ID"),
                    @ApiResponse(code = 200, message = "Properties added!")
            }
    )
    @RequestMapping(method = RequestMethod.PUT, value = "/edit", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> addCarProperties(@RequestParam Integer id, @RequestParam(required = false) String marke,
                                                   @RequestParam(required = false) String model, @RequestParam(required = false) Integer gewicht,
                                                   @RequestParam(required = false) Integer leistung, @RequestParam(required = false) Integer drehmoment,
                                                   @RequestParam(required = false) String farbe, @RequestParam(required = false) Integer tueren,
                                                   @RequestParam(required = false) String klasse, @RequestParam(required = false) String motor_art) {
        LOGGER.debug("PUT Request | Change or add properties of a car");
        if (id == null) {
            LOGGER.error("PUT Request | No ID selected");
            return new ResponseEntity<>("No ID selected", HttpStatus.BAD_REQUEST);
        }
        if (carDataHandler.getCarList().isEmpty()) {
            LOGGER.error("PUT Request | No Cars");
            return new ResponseEntity<>("No Cars", HttpStatus.NOT_FOUND);
        }
        if (!carDataHandler.getCarList().containsKey(id)) {
            LOGGER.error("PUT Request | Invalid ID / No Car found with ID:" + id);
            return new ResponseEntity<>("No Car with this id", HttpStatus.NOT_FOUND);
        }
        if (id != null && !carDataHandler.getCarList().isEmpty() && carDataHandler.getCarList().containsKey(id)) {
            carDataHandler.editCar(id, marke, model, gewicht, leistung, drehmoment, farbe, tueren, klasse, motor_art);
            LOGGER.info("PUT Request | Successfully changed Car with parameter");
        }
        return new ResponseEntity<>("Properties added!", HttpStatus.OK);
    }
}
