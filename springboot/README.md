# Spring boot based micro services

- Build

```shell
maven clean install
```
This will build all jar files required from building docker images.

- Startup

It is recommended to use `docker-compose` to build and deployment docker containers.
You can also run docker commands in each microservice folder to build and run individual containers.

```shell
./startup.sh
```
This command will deploy all spring boot based sample microservices as well as their dependant
containers like mysql database.
If any docker image of the microservices does not exist, it will also build one.

- Shutdown

```shell
./shutdown.sh
```
This command will stop and kill all containers started by the `startup.sh` script.


- Deploy

```shell
mvn clean deploy -Ddocker.user=<username> -Ddocker.password=<passwd>
```

This command deploys the docker image to docker hub