#!/bin/bash

source ./env.sh

export IMAGE=mysql
export IMAGE_TAG=5
export IMAGE_PHPMYADMIN=phpmyadmin/phpmyadmin
export IMAGE_PHPMYADMIN_TAG=latest
export MYSQL_ROOT_PASSWORD=demo
export HOST=mysql5.k8snode1

kubectl create ns "$NS" | true

envsubst < ./template/mysql5.yml | kubectl apply -n "$NS" -f -
