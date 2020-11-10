#!/bin/bash
echo "Deleting all CarSerive Kubernetes configs ..."
kubectl delete -f ./Kubernetes/CarService_Database_Config.yaml
kubectl delete -f ./Kubernetes/CarService_Backend_Config.yaml
kubectl delete -f ./Kubernetes/CarService_Frontend_Config.yaml
kubectl delete -f ./Kubernetes/CarService_Ingress.yaml
echo "Finish - CarService"
