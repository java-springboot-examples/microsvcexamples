# customer sample spring boot based microservice

## Build docker image

- Use docker command

```shell
docker build -t jsu216/microsvc/sb/customer .
```

- Use maven spring boot plugin

```shell
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=jsu216/microsvc/sb/customer
```

## Run docker container

```shell
docker run -e "SPRING_PROFILES_ACTIVE=container" -p 8081:8081 --name customer --link mysql5 jsu216/microsvc/sb/customer
```