package com.nttdata.CarService.entity;

public class Car {

    private Integer id;
    private String marke;
    private String modell;
    private int gewicht;
    private int leistung;
    private int drehmoment;
    private String farbe;
    private int tueren;
    private String klasse;
    private String motor_art;

    public Car() {

    }

    public Car(String marke, String modell, int gewicht, int leistung, int drehmoment, String farbe, int tueren, String klasse, String motor_art) {
        this.marke = marke;
        this.modell = modell;
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

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
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
