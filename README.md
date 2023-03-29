# fun7

### Prerequisites
- Gradle 8
- Java 17
- Docker
- Docker-compose

### How to run
- Clone the project
- Run and build docker containers:
```
docker-compose up -d
```
- Set credentials for Ads external service in [application.yml](src/main/resources/application.yaml):
```
client:
  ads:
    url: <url>
    username: <username>
    password: <password>
```
- Run gradle build
```
gradle build
```
- Run application:
```
java -jar ./build/libs/fun7-0.0.1-SNAPSHOT.jar
```
- Project is now available on http://localhost:8080
- [Postman collection](fun7.postman_collection.json)

### Assumptions
- No authentication is needed
- Ads external service always returns the same result for a certain country code
