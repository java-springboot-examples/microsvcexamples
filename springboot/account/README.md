# account sample spring boot based microservice

## Build docker image

- Use docker command

```shell
docker build -t jsu216/microsvc/sb/account .
```

- Use maven spring boot plugin

```shell
mvn spring-boot:build-image -Dspring-boot.build-image.imageName=jsu216/microsvc/sb/account
```

## Run docker container

```shell
docker run -e "SPRING_PROFILES_ACTIVE=container" -p 8082:8082 --name account --link mysql5 --link customer jsu216/microsvc/sb/account
```
