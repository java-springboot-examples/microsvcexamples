#!/bin/bash

source ./env.sh

export APP=customer-sb
export IMAGE=jsu216/microsvc.sb.customer
export IMAGE_TAG=1.0.0

envsubst < ./template/microsvc.yml | kubectl apply -f -

export APP=account-sb
export IMAGE=jsu216/microsvc.sb.account
export IMAGE_TAG=1.0.0

envsubst < ./template/microsvc.yml | kubectl apply -f -

export APP=transaction-sb
export IMAGE=jsu216/microsvc.sb.transaction
export IMAGE_TAG=1.0.0

envsubst < ./template/microsvc.yml | kubectl apply -f -