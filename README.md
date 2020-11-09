# CarService CRUD REST API
## Description
A simple CRUD REST API in spring boot which can create Cars. You can create, edit, list and delete cars.
## Overview
- [CarService CRUD REST API](#carservice-crud-rest-api)
  - [Description](#description)
  - [Overview](#overview)
  - [Requirements](#requirements)
  - [Setup-Docker](#setup-docker)
  - [Setup-Local](#setup-local)
  - [Doc](#doc)
  - [Tests](#tests)
## Requirements
- Docker and Docker-Compose
- For Local-Setup:
  - Java 8 or higher
  - Maven 3.6.3 or higher
  - PostgreSQL DB Server (12)
## Setup-Docker
- Currently you have to change the IP in the Angular Source Code to connect to the backend.
  - Open ``./CarService-FrontEnd/src/app/car.service.ts`` and change ``192.168.99.101`` to your Docker-Host IP.
- Now you can start docker-compose in the project directory ``docker-compose up -d``
- After that, you should have 3 new docker containers: carservice_frontend, carservice_backend, database
## Setup-Local
- Download the git repo `` git clone https://github.com/LukasLJL/CarService.git`` 
- Import the maven project into your favorite ide (best Intellij)
- Simply start the project in your ide
- an alternative way to start the api:
    - go to the project directory
    - on Windows or Linux etc. run: ``mvn package``
    - now there should be a jar file in the ``target`` folder
    - run ``java -jar ./target/CarService-1.2.0.jar`` to start the api manual
- Now the API should run on ``http://localhost:8080/car/``
- *to make testing easier just run ``createSampleData.sh``
This script will create some sample data*
## Doc
- you can generate a javadoc with ``mvn javadoc:javadoc`` in the project folder. The javadoc files will be generated in ``./target/site/apidocs``
- also, you can use Swagger ``http://DOCKER-HOST-IP:8080/swagger-ui/``
## Tests
- You can test the API with newman tests.
- Simply start the API (see [Setup](#Setup)), then go to the project directory and run ``newman run CarService.postman_collection.json``
