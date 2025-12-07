#!/bin/bash
set -e  # exit on any error

# 1. Start Docker service
echo "Starting Docker..."
sudo systemctl start docker.service

# 2. Run Docker Compose
echo "Running Docker Compose..."
sudo docker compose start

# 3. Export .env variables
if [ -f .env ]; then
  echo "Exporting .env variables..."
  export $(grep -v '^#' .env | xargs)
fi


# # 4. Clean and install dependencies (Maven)
# echo "Building project..."
# mvn clean install

# 5. Run Spring Boot app
echo "Starting Spring Boot application..."
mvn spring-boot:run
