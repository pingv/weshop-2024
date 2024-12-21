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
mvn clean install -DskipTests -Dcheckstyle.skiphttp://localhost:8081/customer/Bauer
```

Spring References:
https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

---------------------------------------
>>> How to Differentiate Between Checkstyle and Compilation Issues

> Run Without Checkstyle To determine if there are any Java compilation issues, run Maven without Checkstyle:

```sh
$ mvn clean install -DskipTests -Dcheckstyle.skip
```

If this succeeds, the issues are purely related to Checkstyle.
If it fails, there are genuine Java compilation issues in your code.

### Check Only Checkstyle Rules To see only Checkstyle violations, you can run:

```sh
$ mvn checkstyle:check
```

This isolates Checkstyle errors and helps you identify all style or formatting issues.

### Enable Debugging for More Details For additional details about the errors, use:

```sh
$ mvn clean install -e -DskipTests
or
$ mvn clean install -X -DskipTests
```

---------------------------------------

### Command to View Active Checkstyle Configuration
Run the following Maven command to display the Checkstyle configuration and the checks it is applying:

```sh
mvn checkstyle:checkstyle
```

This will generate a Checkstyle report (target/site/checkstyle.html) in your project. Open the report in a browser to view details of the checks being performed and the violations in your code.

---------------------------------------