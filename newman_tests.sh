#!/bin/bash

echo "Starting newman tests..."

newman run ./Tests/CarService.postman_collection.json

echo "Done with newman tests..."