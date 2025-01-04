# weshop-2024
Java & Full Spectrum Spring, Spring Boot Projects, GraphQL, REST

## Endpoints
- [http://localhost:8081/actuator/mappings](http://localhost:8081/actuator/mappings)
- [http://localhost:8081/customer](http://localhost:8081/customer)
- [http://localhost:8081/customer/Bauer](http://localhost:8081/customer/Bauer)

## Spring References
- [Spring Data JPA Query Methods](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)

## How to Differentiate Between Checkstyle and Compilation Issues

### Run Without Checkstyle
To determine if there are any Java compilation issues, run Maven without Checkstyle:

```sh
mvn clean install -DskipTests -Dcheckstyle.skip
```

Get jar file:
```sh
mvn clean package

java -jar target/weshop-2024-0.0.1-SNAPSHOT.jar
```

Spring References:
https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

