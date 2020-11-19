#!/bin/bash

#Uninstall prometheus and grafana
helm uninstall prometheus-monitoring prometheus-community/kube-prometheus-stack --namespace monitoring

#Uninstall postgres-exporter 
helm uninstall postgres-exporter prometheus-community/prometheus-postgres-exporter -n monitoring 

#Delete ServiceMonitor for SpringBoot
kubectl delete -f CarService_Prometheus_Monitor.yaml

#Delete CRDs
kubectl delete crd alertmanagerconfigs.monitoring.coreos.com
kubectl delete crd alertmanagers.monitoring.coreos.com
kubectl delete crd podmonitors.monitoring.coreos.com
kubectl delete crd probes.monitoring.coreos.com
kubectl delete crd prometheuses.monitoring.coreos.com
kubectl delete crd prometheusrules.monitoring.coreos.com
kubectl delete crd servicemonitors.monitoring.coreos.com
kubectl delete crd thanosrulers.monitoring.coreos.com
