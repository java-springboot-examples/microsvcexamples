#!/bin/bash

source ./env.sh

./undeploy_svc.sh transaction-sb
./undeploy_svc.sh account-sb
./undeploy_svc.sh customer-sb