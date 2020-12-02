# transaction sample spring boot based microservice

## Build docker image

- Use docker command

```shell
docker build -t jsu216/microsvc/sb/transaction .
```

- Use maven spring boot plugin

```shell
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=jsu216/microsvc/sb/transaction
```

## Run docker container

```shell
docker run -e "SPRING_PROFILES_ACTIVE=container" -p 8083:8083 --name transaction --link mysql5 --link account jsu216/microsvc/sb/transaction
```