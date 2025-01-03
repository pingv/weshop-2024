# Documentation for Static Code Analysis tools

## Checkstyle
- https://checkstyle.sourceforge.io/
- *See pom.xml <artifactId>maven-checkstyle-plugin</artifactId> for specific configurations and rules.*
- Checkstyle automates the process of checking Java code to enforce coding standards.
- Checkstyle is highly configurable and can be made to support almost any coding standard. 
- An example configuration files are supplied supporting the Sun Code Conventions, Google Java Style.

---------------------------------------
#### How to Differentiate Between Checkstyle and Compilation Issues?
- Run Without Checkstyle To determine if there are any Java compilation issues, run Maven without Checkstyle:

```sh
$ mvn clean install -DskipTests -Dcheckstyle.skip
```

  - If this succeeds, the issues are purely related to Checkstyle.
  - If it fails, there are genuine Java compilation issues in your code.

#### Run only a Specific Checkstyle Configuration
```sh
$ mvn checkstyle:check -Dcheckstyle.config.location=config/checkstyle/custom_checks.xml
```

#### Check Only Checkstyle Rules To see only Checkstyle violations, you can run:

```sh
$ mvn checkstyle:check
```

This isolates Checkstyle errors and helps you identify all style or formatting issues.

#### Enable Debugging for More Details For additional details about the errors, use:

```sh
$ mvn clean install -e -DskipTests
or
$ mvn clean install -X -DskipTests
```

---------------------------------------

#### Command to View Active Checkstyle Configuration
Run the following Maven command to display the Checkstyle configuration and the checks it is applying:

```sh
mvn checkstyle:checkstyle
```

This will generate a Checkstyle report (target/site/checkstyle.html) in your project. Open the report in a browser to view details of the checks being performed and the violations in your code.

---------------------------------------

## Sonar
- https://www.sonarqube.org/