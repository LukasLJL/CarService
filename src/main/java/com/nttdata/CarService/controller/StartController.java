package com.nttdata.CarService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * start page
 * @author Lukas
 */
@EnableSwagger2
@RestController
public class StartController {

    @RequestMapping("/start")
    public String start (){
        return "<h1>CAR Service</h1>";
    }

}
