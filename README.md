# GodelTechnologiesMastery
This is the mastery progect for Godel Technologies company HR

REST api documentation available in http://localhost:8080/swagger-ui.html#

Shutdown point available by curl -X POST localhost:8080/actuator/shutdown

Application uses 2 different DB (one for DAO test and other to production), each other defines in application.properties

Application logs REST requests and exceptions in file named test.log


To create docker image run:
sudo docker build -t mastery-in-docker .

To start application run:
docker-compose up