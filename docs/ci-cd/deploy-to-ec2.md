# EC2 Deployment and Configuration Guide

## 1. Overview
This comprehensive guide covers the deployment of this Spring Boot application on EC2 specifically via GitHub Actions as specified on deploy-to-ec2.yml script. Includes GitHub Actions automation, H2 database setup, and service configuration that were required for EC2.

## 2. GitHub Actions Deployment Setup

### 2.1 Workflow File
Location: `.github/workflows/deploy-to-ec2.yml`

```yaml
name: Deploy to EC2

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build-and-deploy:
    runs-on: ubuntu-latest
    steps:
      # ... [See complete workflow file in repository]
```

### 2.2 Required GitHub Secrets
Configure at: `https://github.com/{owner}/{repo}/settings/secrets/actions`

- `AWS_ACCESS_KEY_ID`: IAM user access key
- `AWS_SECRET_ACCESS_KEY`: IAM user secret key
- `AWS_REGION`: Target region (e.g., us-east-1)
- `EC2_HOST`: Instance public IP/DNS
- `EC2_SSH_KEY`: SSH private key
- `EC2_USERNAME`: Username (ec2-user)

## 3. Infrastructure Setup

### 3.1 Application Structure
```
/opt/myapp/
├── app.jar
└── data/
    ├── testdb.mv.db
    └── testdb.trace.db
```

### 3.2 Initial Server Setup
```bash
# Create directory structure
sudo mkdir -p /opt/myapp/data
sudo chown -R ec2-user:ec2-user /opt/myapp
sudo chmod 755 /opt/myapp/data

# Verify permissions
ls -la /opt/myapp/data
```

### 3.3 Security Configuration
- Configure EC2 security group:
  - Inbound rule for port 8081 (HTTP)
- Set up IAM user with minimal permissions:
```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "ec2:DescribeInstances",
                "ec2:DescribeInstanceStatus"
            ],
            "Resource": "*"
        }
    ]
}
```

## 4. Database Configuration

### 4.1 Production Settings
File: `application-prod.properties`
```properties
# Database Configuration
spring.datasource.url=jdbc:h2:file:/opt/myapp/data/testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# H2 Console Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true
```

### 4.2 Development Settings
File: `application-dev.properties`
```properties
spring.datasource.url=jdbc:h2:file:./data/testdb
```

## 5. Service Management

### 5.1 SystemD Commands
```bash
# Status check
sudo systemctl status springapp

# Start/Stop/Restart
sudo systemctl start springapp
sudo systemctl stop springapp
sudo systemctl restart springapp

# View logs
sudo journalctl -u springapp.service -f
sudo journalctl -u springapp.service -n 100
```

### 5.2 Database Maintenance
```bash
# Backup database
sudo systemctl stop springapp
sudo cp /opt/myapp/data/testdb.mv.db /backup/
sudo cp /opt/myapp/data/testdb.trace.db /backup/
sudo systemctl start springapp

# Monitor size
du -sh /opt/myapp/data/testdb.mv.db
df -h /opt/myapp/data
```

## 6. Deployment Verification

### 6.1 Checklist
1. Verify service status:
   ```bash
   sudo systemctl status springapp
   ```
2. Check application logs:
   ```bash
   sudo journalctl -u springapp.service -n 50
   ```
3. Test endpoints:
   ```
   http://<EC2-IP>:8081 (Static Page / index.html)
   http://<EC2-IP>:8081/actuator/health
   http://<EC2-IP>:8081/h2-console
   ```

### 6.2 Common Issues and Solutions

#### Application Won't Start
1. Check permissions:
   ```bash
   sudo chown -R ec2-user:ec2-user /opt/myapp
   sudo chmod 700 /opt/myapp/data
   ```
2. Verify service configuration:
   ```bash
   sudo systemctl cat springapp
   ```
3. Check logs for errors:
   ```bash
   sudo journalctl -u springapp.service -n 100
   ```

#### Database Issues
1. Database locked:
   - Stop application
   - Check for multiple connections
   - Verify process ownership
2. Corruption:
   - Restore from backup
   - Check disk space
   - Verify file permissions

## 7. Maintenance Tasks

### 7.1 Regular Checks
1. Monitor disk space
2. Review application logs
3. Check service status
4. Verify backup integrity

### 7.2 Security Maintenance
1. Rotate AWS access keys
2. Update GitHub secrets
3. Review security group rules
4. Check service permissions

### 7.3 Performance Optimization
1. Monitor response times
2. Check database indexes
3. Review memory usage
4. Analyze log patterns
