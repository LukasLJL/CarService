package com.nttdata.CarService;


import com.nttdata.CarService.controller.CarControllerTest;
import com.nttdata.CarService.controller.CarControllerTest_IntegrationTest;
import com.nttdata.CarService.handler.CarDataHandlerTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        CarControllerTest.class,
        CarDataHandlerTest.class,
        CarControllerTest_IntegrationTest.class
})
public class TestAll {
}
