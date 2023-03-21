# courier-tracking

## Building the project
 
You will need:
- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html "Java JDK 17")
- [Maven 3.9.1](https://dlcdn.apache.org/maven/maven-3/3.9.1/binaries/apache-maven-3.9.1-bin.zip "Maven 3.9.1")
- [Docker](https://www.docker.com "Docker") (For RabbitMQ)

## Libraries used

- Spring Boot 3.0.5-SNAPSHOT
- RabbitMQ 3.11
- H2 Database
- Lombok
- ModelMapper
- Swagger UI
- Junit


## Do the following step by step
- RabbitMQ should be run first.
- Then the Spring Boot application can be run.
- You need to login to the h2 console and run the script under /src/main/resources/scripts. So everything is ready


## RabbitMQ

You can use the following docker command to run rabbitmq.

```docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management```

Once RabbitMQ is running, you can connect to the RabbitMQ interface by opening the following link in the browser.
<http://localhost:15672/>

- Username: guest
- Password: guest

![rabbitmq_login](https://user-images.githubusercontent.com/17949405/226562953-b8089b22-2d5d-4331-a2ee-bc8a14439a5b.png)

## H2 Database Console

<http://localhost:8080/h2-console/>

- JDBC Url: jdbc:h2:mem:courier_tracking_db;
- User Name: admin
- Password: admin

![h2_console](https://user-images.githubusercontent.com/17949405/226562105-1ac2e0eb-eb95-4b8a-9977-89ab368d0c8a.png)

After logging in, run the script under /src/main/resources/scripts/ as follows.

![h2_script_run](https://user-images.githubusercontent.com/17949405/226584543-5c99b902-a56e-43be-b3c3-5bd4a6980e90.png)


## Swagger UI

You can view the api document with the swagger ui interface link below 
- <http://localhost:8080/swagger-ui/index.html>

## Example Requests

POST http://localhost:8080/courierMove 
- When you make a request, the input values push into the RabbitMQ queue and the consumer logs the courier that meets the required condition.

```json
{
  "courierId": 1,
  "lat": 40.9930802,
  "lng": 29.12405
}
```

GET http://localhost:8080/courierTotalTravelDistance
- When you make a request, it returns the total travel distance for the courierId given in the input.

```json
{
  "courierId": 1
}
```