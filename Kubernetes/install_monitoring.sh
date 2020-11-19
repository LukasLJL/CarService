#!/bin/bash

#create namespace for monitoring
kubectl create namespace monitoring

# add repo for prometheus-operator
helm repo add stable https://charts.helm.sh/stable
# community repo
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts

#update helm
helm repo update

#install prometheus and grafana
helm install prometheus-monitoring prometheus-community/kube-prometheus-stack --namespace monitoring

#install postgres-exporter
helm install postgres-exporter prometheus-community/prometheus-postgres-exporter -n monitoring -f Postgres_Exporter.yaml --set config.datasource.host=database.default --set config.datasource.password=admin

#install servicemonitor for CarSerive Backend (SpringBoot)
kubectl apply -f CarService_Prometheus_Monitor.yaml





