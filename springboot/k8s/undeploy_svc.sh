#!/bin/bash

if [ -z "$1" ]
then
  echo "Please specify the NAMElication name to be undeployed!"
  exit
fi

source ./env.sh

NAME=$1
echo "Undeploy the NAME $NAME ..."

kubectl -n "$NS" delete ingress $NAME
kubectl -n "$NS" delete service $NAME
kubectl -n "$NS" delete deployment $NAME

kubectl -n "$NS" delete servicemonitor $NAME -n $NS_PROMETHEUS