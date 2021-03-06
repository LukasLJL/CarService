# CarService CRUD REST API
## Description
A simple CRUD REST API in spring boot which can create Cars. You can create, edit, list and delete cars.
## Overview
- [CarService CRUD REST API](#carservice-crud-rest-api)
  - [Description](#description)
  - [Overview](#overview)
  - [Requirements](#requirements)
  - [Setup-Docker](#setup-docker)
  - [Setup-Kubernetes (minikube)](#setup-kubernetes-minikube)
  - [Setup-Local](#setup-local)
  - [Monitoring and Kubernetes](#monitoring-and-kubernetes)
  - [Doc](#doc)
    - [Swagger](#swagger)
  - [Tests](#tests)
  - [Scripts](#scripts)
## Requirements
- Docker and Docker-Compose
- Minikube
- For Local-Setup:
  - Java 8 or higher
  - Maven 3.6.3 or higher
  - PostgreSQL DB Server (12)
## Setup-Docker
- Currently you have to change your Host-File to use the Frontend correctly
  - Windows -> Open Host-File as Admin and add ``192.168.99.101 backend-app`` the IP should be your Docker-Host IP-Address.
- Now you can start docker-compose in the project directory ``docker-compose up -d``
- After that, you should have 3 new docker containers: carservice_frontend, carservice_backend, database
## Setup-Kubernetes (minikube)
*Tested with minikube on Windows*
- First you have to Build the Docker Images.. but you should be connected to the Kubernetes (minikube) docker-system
  - For this step execute ``eval "$(minikube docker-env --shell=bash)"``, now you can perform docker commands and they will be executed at the kubernetes docker-system
  - Now you can run a script to build all required images ``buildImages.sh``
- You can simply run a script to apply all K8 CarService Configs ``./K8Configs.sh``
- Now everything should be running, the frontend is accessible at ``http://minikube/``
- If you want you can delete all K8 CarService Configs with a script ``./K8Delete.sh``
## Setup-Local
- Import the maven project into your favorite ide (best Intellij)
- Simply start the project in your ide
- an alternative way to start the api:
    - go to the project directory
    - on Windows or Linux etc. run: ``mvn package``
    - now there should be a jar file in the ``target`` folder
    - run ``java -jar ./target/CarService-1.2.0.jar`` to start the api manual
- Now the API should run on ``http://localhost:8080/car/``
## Monitoring and Kubernetes
[Kubernetes Configs](Kubernetes/README.md)
## Doc
- you can generate a javadoc with ``mvn javadoc:javadoc`` in the project folder. The javadoc files will be generated in ``./target/site/apidocs``
### Swagger
- Local -> ``http://localhost:8080/swagger-ui/``
- Docker -> ``http://DOCKER-HOST-IP:8080/swagger-ui/``
- Kubernetes -> ``http://minikube:30002/swagger-ui/``
  - Replace ``minikube`` if you are not using minikube
## Tests
- You can test the API with newman tests.
- Simply start the API, then go to the project directory and run the test script ``./newman_tests.sh <platform>``
  - Local -> ``./newman_tests.sh local``
  - Docker -> ``./newman_tests.sh docker``
  - Kubernetes -> ``./newman_tests.sh kubernetes``
## Scripts
-  *to make testing easier just run ``createSampleData.sh <platform>`` This script will create some sample data*
  - You may have to change the IP for the Platform which you are using. Just edit the script ``createSampleData.sh ``
  - Local -> ``./createSampleData.sh local``
  - Docker -> ``./createSampleData.sh docker``
  - Kubernetes -> ``./createSampleData.sh kubernetes``
