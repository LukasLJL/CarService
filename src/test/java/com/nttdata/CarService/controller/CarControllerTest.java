package com.nttdata.CarService.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CarControllerTest {

    //JSON TESTING
    @Test
    @Order(1)
    void addCarPropertiesWhileNoCarExits() throws JSONException {

        JSONObject car = new JSONObject();
        car.put("id", 200);
        car.put("leistung", 200);
        car.put("motor_art", "benzin");

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080/car/edit";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(car.toString(), headers);

        try {
            ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, request, String.class);
            fail();
        } catch (HttpClientErrorException ex) {
            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }

    @Test
    @Order(2)
    void testCreateCar() throws JSONException {
        JSONObject car = new JSONObject();
        car.put("marke", "Mercedes");
        car.put("modell", "A-Klasse");

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080/car/create";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<String>(car.toString(), headers);
        ResponseEntity<String> result = restTemplate.postForEntity(baseUrl, request, String.class);
        HttpEntity<String> request2 = new HttpEntity<String>(car.toString(), headers);
        ResponseEntity<String> result2 = restTemplate.postForEntity(baseUrl, request2, String.class);

        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(HttpStatus.CREATED, result2.getStatusCode());
    }

    //JSON
    @Test
    @Order(3)
    void addCarPropertiesJSON() throws JSONException {
        JSONObject car1 = new JSONObject();
        JSONObject car2 = new JSONObject();
        JSONObject car3 = new JSONObject();

        //Car with no ID
        car1.put("leistung", 200);
        car1.put("motor_art", "benzin");

        //Car with Invalid ID
        car2.put("id", -1);
        car2.put("leistung", 200);
        car2.put("motor_art", "benzin");

        //Car with real ID
        car3.put("id", 0);
        car3.put("leistung", 200);
        car3.put("motor_art", "benzin");


        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080/car/edit";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request_noID = new HttpEntity<String>(car1.toString(), headers);
        HttpEntity<String> request_invID = new HttpEntity<String>(car2.toString(), headers);
        HttpEntity<String> request_realID = new HttpEntity<String>(car3.toString(), headers);


        //test real id
        ResponseEntity<String> result_realID = restTemplate.exchange(baseUrl, HttpMethod.PUT, request_realID, String.class);
        Assertions.assertEquals(HttpStatus.OK, result_realID.getStatusCode());


        //test without id
        try {
            ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, request_noID, String.class);
            fail();
        } catch (HttpClientErrorException ex) {
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());

        }

        //test with invalid id
        try {
            ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.PUT, request_invID, String.class);
            fail();
        } catch (HttpClientErrorException ex) {
            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }

    @Test
    @Order(4)
    void deleteCarJSON() throws JSONException {
        JSONObject car1 = new JSONObject();
        JSONObject car2 = new JSONObject();

        //Test delete function
        car1.put("id", 1);
        //Test delete function without ID (error handling)
        car2.put("id", -1);

        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:8080/car/delete";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //test for valid id
        HttpEntity<String> request = new HttpEntity<String>(car1.toString(), headers);
        ResponseEntity<String> result = restTemplate.exchange(baseUrl, HttpMethod.DELETE, request, String.class);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        //test for invalid id
        HttpEntity<String> request2 = new HttpEntity<String>(car2.toString(), headers);
        try {
            ResponseEntity<String> result2 = restTemplate.exchange(baseUrl, HttpMethod.DELETE, request2, String.class);
            fail();
        } catch (HttpClientErrorException ex) {
            Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());

        }
    }
}