#!/bin/bash
echo "Applying all CarSerive Kubernetes configs ..."
kubectl apply -f ./Kubernetes/CarService_Database_Config.yaml
kubectl apply -f ./Kubernetes/CarService_Backend_Config.yaml
kubectl apply -f ./Kubernetes/CarService_Frontend_Config.yaml
kubectl apply -f ./Kubernetes/CarService_Ingress.yaml
echo "Finish - CarService"
