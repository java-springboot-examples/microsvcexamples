#!/bin/bash

source ./env.sh

kubectl delete ns "$NS" | true

kubectl create ns "$NS"

kubectl label namespace "$NS" istio-injection=enabled