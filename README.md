# CarService CRUD REST API
## Description
A simple CRUD REST API in spring boot which can create Cars. You can create, edit, list and delete cars.
## Overview
- [CarService CRUD REST API](#carservice-crud-rest-api)
  - [Description](#description)
  - [Overview](#overview)
  - [Requirements](#requirements)
  - [Setup](#setup)
  - [HowToUse](#howtouse)
  - [Doc](#doc)
  - [Tests](#tests)
## Requirements
- Java 8 or higher
- Maven 3.6.3 or higher
## Setup
- Download the git repo `` git clone https://github.com/LukasLJL/CarService.git`` 
- Import the maven project into your favorite ide (best Intellij)
- Simply start the project in your ide
- an alternativ way to start the api:
    - go to the project directory
    - on Windows or Linux etc. run: ``mvn package``
    - now there should be a jar file in the ``target`` folder
    - run ``java -jar ./target/CarService-1.0.0.jar`` to start the api manual
- Now the API should run on ``http://localhost:8080/car/``
- *to make testing easier just run ``createSampleData.cmd`` or ``createSampleData.sh``
This script will create some sample data*
## HowToUse
A Car can have to following properties:
- marke - brand
- model - model 
- gewicht - weight
- leistung - power
- drehmoment - torque
- farbe - color
- tueren - number of doors
- klasse - car typ (cabrio, coupe, etc.)
- motor_art - engine typ (diesel, gasoline, electric)
## Doc
- you can generate a javadoc with ``mvn javadoc:javadoc`` in the project folder. The javadoc files will be generated in ``./target/site/apidocs``
- also, you can use Swagger ``http://localhost:8080/swagger-ui/``
## Tests
- You can test the API with newman tests.
- Simply start the API (see [Setup](#Setup)), then go to the project directory and run ``newman run CarService.postman_collection.json``
