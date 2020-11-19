# Kubernetes Configs

## Description 
All Kubernetes Configs in one place to host the whole application on Kubernetes.
You can also install some monitoring software (prometheus and grafana) to monitor your system. 

---

## Configs
### Application Configs
- [``CarService_Backend_Config.yaml``](./CarService_Backend_Config.yaml)
- [``CarService_Database_Config.yaml``](./CarService_Database_Config.yaml)
- [``CarService_Frontend_Config.yaml``](./CarService_Frontend_Config.yaml)

### Secrets
- [``CarService_Database_Secret.yaml``](./CarService_Database_Secret.yaml)

### Ingress
- [``CarService_Ingress.yaml``](./CarService_Ingress.yaml)

---

## Monitoring
If you want you can monitor your Kubernetes system and the backend application.
For monitoring the system uses Grafana and Prometheus.


### Monitoring Configs
- [``CarService_Prometheus_Monitor.yaml``](./CarService_Prometheus_Monitor.yaml)
- [``Postgres_Exporter.yaml``](./Postgres_Exporter.yaml)
### Monitoring Dashboard
- [``CarService-Backend-Application-1605771562499.json``](./CarService-Backend-Application-1605771562499.json)

### Install Monitoring
- Just run the script which will install all necessary things ``./install_monitoring.sh``
- If you want to get rid of the monitoring system you can also uninstall it with ``./uninstall_monitoring.sh``
- All Deployments, Services etc. will be installed to a separate namespace called ``monitoring``

### Access Grafana and Prometheus
- You need to port-forward grafana or prometheus to have access to it.
- Prometheus -> ``kubectl port-forward -n monitoring service/prometheus-monitoring-kube-prometheus 9090``
  - Open your Web-Browser and got to ``localhost:9090``
- Grafana -> ``kubectl port-forward -n monitoring service/prometheus-monitoring-grafana 3000:80``
  - Open your Web-Browser and got to ``localhost:9090``
    - User: admin
    - Password: prom-operator
  - I create a Dashboard for the CarService Backend, you can simply import the ``CarService-Backend-Application-1605771562499.json`` file in Grafana.
    - ``http://localhost:3000/dashboards`` -> Import Button -> Upload JSON file

