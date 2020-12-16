# How to deploy to k8s cluster

This folder is only for deployment to k8s cluster

Before deploy or undeploy, confirm the settings in `env.sh` are correctly.

## Deploy

```shell
./deploy.sh
```

## Undeploy

Remove all services

```shell
./undeploy_allsvc.sh
```

Remove an individual service

```shell
./undeploy.sh customer-sb
```
