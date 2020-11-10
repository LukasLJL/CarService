#!/bin/bash

echo "Starting newman tests..."

newman run ./Tests/CarService.postman_collection.json -e ./Tests/$1.postman_environment.json 

echo "Done with newman tests..."