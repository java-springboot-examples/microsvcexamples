# Microservice Examples

## Prerequisites

- Database

Some the microservices requires a MySql instance to be running locally at port 3306.

If not run the following commands:

```shell
MYSQL_ROOT_PASSWORD=demo
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} --name mysql5 mysql:5
docker run -d -p 3380:80 --link mysql5 -e PMA_HOST=mysql5 --name myadmin phpmyadmin/phpmyadmin:latest
```

- Docker

Docker and docker-compose is required to be installed.