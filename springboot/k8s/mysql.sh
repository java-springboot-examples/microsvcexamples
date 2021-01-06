#!/bin/bash

source ./env.sh

export APP=mysql5
export IMAGE=mysql
export IMAGE_TAG=5
export APP_PHPMYADMIN=phpmyadmin
export IMAGE_PHPMYADMIN=phpmyadmin/phpmyadmin
export IMAGE_PHPMYADMIN_TAG=latest
export MYSQL_ROOT_PASSWORD=demo

envsubst < ./template/mysql5.yml | kubectl apply -f -
