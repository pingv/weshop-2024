# H2 Database Configuration Guide for Spring Boot

## Overview
This guide covers how to properly configure H2 database in a Spring Boot application to allow simultaneous access from both the application and H2 Console, specifically addressing connection and console access issues.

## Configuration
### IntelliJ -> H2 Database Driver was downloaded from https://www.jetbrains.com/datagrip/jdbc-drivers/
### Basic Application Properties
```properties
See application.properties
```
## Key Features

### Important Configuration Parameters
- `AUTO_SERVER=TRUE`: Enables multiple simultaneous connections to the database - This isn't true???
- `spring.h2.console.enabled=true`: Activates the H2 web console
- `spring.h2.console.settings.web-allow-others=true`: Ensures console accessibility

### File-Based Storage
The configuration uses file-based storage (`jdbc:h2:file:./data/testdb`) rather than in-memory storage, ensuring data persistence between application restarts.

## Accessing H2 Console

### Steps to Access
1. Start the Spring Boot application
2. Navigate to `http://localhost:8081/h2-console` in your web browser *** this will work only if the Spring Boot application is running ***
3. Enter the following connection details:
   - Driver Class: `org.h2.Driver`
   - JDBC URL: `jdbc:h2:file:/Users/vishnuparandhaman/code/weshop-2024/data/testdb`
   - Username: `sa`
   - Password: (leave empty)

### Connection Details for IDE Database Tools
Use the same connection details when connecting through IDE database tools (e.g., IntelliJ's database browser):
- URL: `jdbc:h2:file:/Users/vishnuparandhaman/code/weshop-2024/data/testdb`
- Username: `sa`
- Password: (empty)

### Connection via Command Line
- vishnuparandhaman@Vishnus-MacBook-Air-2 libs % java -jar h2-2.3.232.jar -tcp -tcpAllowOthers -web
- Web Console server running at http://localhost:8082?key=314b7cb9b143844838afaa4d49356da33844f7133ad552e64aa40b1eb3235c92 (only local connections)
- TCP server running at tcp://localhost:9092 (others can connect)

Access the H2 Console at http://localhost:8082/login.jsp?jsessionid=bc1323ae3efc5a50ee177830988de15b

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

### Queries on h2-console

- SHOW COLUMNS FROM EMPLOYEE;