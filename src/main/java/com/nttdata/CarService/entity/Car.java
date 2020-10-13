package com.nttdata.CarService.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Car")
public class Car {

    @ApiModelProperty(notes = "ID of car", example = "1", position = 0)
    private Integer id;
    @ApiModelProperty(notes = "brand of a car", example = "Volkswagen", position = 1)
    private String marke;
    @ApiModelProperty(notes = "model", example = "Golf", position = 2)
    private String model;
    @ApiModelProperty(notes = "weight of a car", example = "1540", position = 3)
    private int gewicht;
    @ApiModelProperty(notes = "power of a car", example = "120", position = 4)
    private int leistung;
    @ApiModelProperty(notes = "torque of a car", example = "220", position = 5)
    private int drehmoment;
    @ApiModelProperty(notes = "color of a car", example = "blue", position = 6)
    private String farbe;
    @ApiModelProperty(notes = "number of doors", example = "3", position = 7)
    private int tueren;
    @ApiModelProperty(notes = "car typ", example = "Cabrio", position = 8)
    private String klasse;
    @ApiModelProperty(notes = "motor typ", example = "diesel", position = 9)
    private String motor_art;

    public Car() {

    }

    public Car(String marke, String model, int gewicht, int leistung, int drehmoment, String farbe, int tueren, String klasse, String motor_art) {
        this.marke = marke;
        this.model = model;
        this.gewicht = gewicht;
        this.leistung = leistung;
        this.drehmoment = drehmoment;
        this.farbe = farbe;
        this.tueren = tueren;
        this.klasse = klasse;
        this.motor_art = motor_art;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getLeistung() {
        return leistung;
    }

    public void setLeistung(int leistung) {
        this.leistung = leistung;
    }

    public int getDrehmoment() {
        return drehmoment;
    }

    public void setDrehmoment(int drehmoment) {
        this.drehmoment = drehmoment;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public int getTueren() {
        return tueren;
    }

    public void setTueren(int tueren) {
        this.tueren = tueren;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getMotor_art() {
        return motor_art;
    }

    public void setMotor_art(String motor_art) {
        this.motor_art = motor_art;
    }
}
