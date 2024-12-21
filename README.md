# weshop-2024
Java &amp; Full Spectrum Spring, Spring Boot Projects, GraphQL, REST

http://localhost:8081/actuator/mappings
http://localhost:8081/customer
http://localhost:8081/customer/Bauer


Spring References:
https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

---------------------------------------
>>> How to Differentiate Between Checkstyle and Compilation Issues

> Run Without Checkstyle To determine if there are any Java compilation issues, run Maven without Checkstyle:

$ mvn clean install -DskipTests -Dcheckstyle.skip

If this succeeds, the issues are purely related to Checkstyle.
If it fails, there are genuine Java compilation issues in your code.

> Check Only Checkstyle Rules To see only Checkstyle violations, you can run:

$ mvn checkstyle:check

This isolates Checkstyle errors and helps you identify all style or formatting issues.

> Enable Debugging for More Details For additional details about the errors, use:
$ mvn clean install -e -DskipTests
or
$ mvn clean install -X -DskipTests
---------------------------------------