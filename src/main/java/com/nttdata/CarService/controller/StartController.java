package com.nttdata.CarService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    @RequestMapping("/")
    public String start (){
        return "<h1>CAR Service</h1>";
    }

}
