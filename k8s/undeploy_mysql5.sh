#!/bin/bash

source ./env.sh

kubectl -n "$NS" delete ingress "$APP"
kubectl -n "$NS" delete service "$APP"
kubectl -n "$NS" delete deployment "$APP"