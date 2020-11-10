#!/bin/bash
echo "Building Docker Images..."
docker build -f ./Docker/Dockerfile-SpringBoot . -t carservice_springboot_backend
docker build -f ./Docker/Dockerfile-Angular . -t carservice_angular_frontend
echo "Finish - Building Images"