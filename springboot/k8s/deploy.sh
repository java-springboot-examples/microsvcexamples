#!/bin/bash

source ./env.sh

export NAME=customer-sb
export IMAGE=jsu216/microsvc.sb.customer
export IMAGE_TAG=1.0.2

envsubst < ./template/microsvc.yml | kubectl apply -f -

export NAME=account-sb
export IMAGE=jsu216/microsvc.sb.account
export IMAGE_TAG=1.0.2

envsubst < ./template/microsvc.yml | kubectl apply -f -

export NAME=transaction-sb
export IMAGE=jsu216/microsvc.sb.transaction
export IMAGE_TAG=1.0.2

envsubst < ./template/microsvc.yml | kubectl apply -f -