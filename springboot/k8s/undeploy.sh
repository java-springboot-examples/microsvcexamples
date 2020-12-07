#!/bin/bash

if [ -z "$1" ]
then
  echo "Please specify the application name to be undeployed!"
  exit
fi

source ./env.sh

APP=$1
echo "Undeploy the app $APP ..."

kubectl -n "$NS" delete ingress $APP
kubectl -n "$NS" delete service $APP
kubectl -n "$NS" delete deployment $APP