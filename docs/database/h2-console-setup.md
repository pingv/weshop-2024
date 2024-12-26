# H2 Database Configuration Guide for Spring Boot

## Overview
This guide covers how to properly configure H2 database in a Spring Boot application to allow simultaneous access from both the application and H2 Console, specifically addressing connection and console access issues.

## Configuration
### IntelliJ -> H2 Database Driver was downloaded from https://www.jetbrains.com/datagrip/jdbc-drivers/
### Basic Application Properties
```properties
# Server Configuration
spring.application.name=spring-boot
server.port=8081

# H2 Console Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true

# Datasource Configuration
spring.datasource.url=jdbc:h2:file:./data/testdb;AUTO_SERVER=TRUE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA and Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Connection Pool Configuration
spring.datasource.hikari.maximum-pool-size=5
```

## Key Features

### Important Configuration Parameters
- `AUTO_SERVER=TRUE`: Enables multiple simultaneous connections to the database
- `spring.h2.console.enabled=true`: Activates the H2 web console
- `spring.h2.console.settings.web-allow-others=true`: Ensures console accessibility

### File-Based Storage
The configuration uses file-based storage (`jdbc:h2:file:./data/testdb`) rather than in-memory storage, ensuring data persistence between application restarts.

## Accessing H2 Console

### Steps to Access
1. Start the Spring Boot application
2. Navigate to `http://localhost:8081/h2-console` in your web browser
3. Enter the following connection details:
   - Driver Class: `org.h2.Driver`
   - JDBC URL: `jdbc:h2:file:./data/testdb`
   - Username: `sa`
   - Password: (leave empty)

### Connection Details for IDE Database Tools
Use the same connection details when connecting through IDE database tools (e.g., IntelliJ's database browser):
- URL: `jdbc:h2:file:./data/testdb`
- Username: `sa`
- Password: (empty)

## Troubleshooting

### Common Issues and Solutions
1. **Console Access Issues**
   - Verify the correct port (same as server.port)
   - Ensure H2 Console is enabled
   - Check for correct connection URL

2. **Multiple Connection Issues**
   - Confirm `AUTO_SERVER=TRUE` is in the datasource URL
   - Check for database file locks
   - Verify no other processes are blocking the database file

3. **Database Reset**
   - Delete the database files in the ./data directory
   - Restart the application to recreate the database

## Best Practices

### Development Environment
1. Use file-based storage for data persistence
2. Enable H2 Console for easy database management
3. Configure appropriate logging levels for SQL statements
4. Set reasonable connection pool sizes

### Security Considerations
1. Disable H2 Console in production
2. Change default credentials in production environments
3. Limit web-allow-others in production settings

## Additional Notes
- The configuration provided is optimized for development environments
- Adjust security settings appropriately for production deployment
- Regular database backups are recommended when using file-based storage
