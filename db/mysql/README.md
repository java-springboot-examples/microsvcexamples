# MySql

## Run mysql and admin console as local docker containers

```shell script
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=demo --name demo_mysql5 mysql:5
docker run -d -p 3380:80 --link demo_mysql5 -e PMA_HOST=demo_mysql5 --name demo_myadmin phpmyadmin/phpmyadmin:latest
```
