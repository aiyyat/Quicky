# Quiz Admin Service
Reference: [Documentation](https://www.playframework.com/documentation/2.8.x/Home).

### Flyway Migration commands
* sbt flywayClean
* sbt flywayMigrate
* sbt flywayInfo

### Docker Compose
* docker-compose up question-db

### 
$ sbt dist
$ ./prepare.sh
$ sudo docker build . -t kaliwrath/quiz_admin_service
$ sudo docker login
$ sudo docker push kaliwrath/quiz_admin_service
