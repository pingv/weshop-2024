#!/bin/bash
# Update system packages
sudo yum update -y

# Install Java 21
sudo yum install -y java-21-amazon-corretto

# Verify Java installation
java -version

# Create directory for your application
sudo mkdir -p /opt/myapp

# Set permissions
sudo chmod 755 /opt/myapp

# Optional: Create a service user for running the application
sudo useradd -r -s /sbin/nologin springapp

# Optional: Set ownership of app directory
sudo chown springapp:springapp /opt/myapp

# Create systemd service file for your Spring Boot application
cat << 'EOF' > /etc/systemd/system/springapp.service
[Unit]git
Description=Spring Boot Application
After=network.target

[Service]
Type=simple
User=springapp
ExecStart=/usr/bin/java -jar /opt/myapp/app.jar
SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
EOF

# Set permissions for service file
sudo chmod 644 /etc/systemd/system/springapp.service

# Reload systemd to recognize new service
sudo systemctl daemon-reload

# Enable service to start on boot
sudo systemctl enable springapp