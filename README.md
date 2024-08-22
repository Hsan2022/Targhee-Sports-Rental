# Targhee Sports Rental

## Overview

The Targhee Sports Rental application is designed to manage sports equipment rentals. This repository includes the core application code along with integration tests for the DAO (Data Access Object) layer. The DAO tests ensure the correctness of data retrieval operations for the `Order`, `OrderDetail`, and `Product` entities.

## Project Structure

- **src/main/java**: Contains the main application code, including entity classes and DAOs.
- **src/test/java**: Contains integration tests for DAOs.
    - `OrderDAOTest`: Tests the `OrderDAO` for retrieving `Order` entities.
    - `OrderDetailsDAOTest`: Tests the `OrderDetailsDAO` for retrieving `OrderDetail` entities.
    - `ProductDAOTest`: Tests the `ProductDAO` for retrieving `Product` entities.
- **src/main/resources**: Configuration files and other resources.
- **src/test/resources**: Test configuration files.

## Prerequisites

- Java 17 or higher
- Maven or Gradle (for dependency management)
- A relational database (e.g., H2, PostgreSQL) configured in your application properties

## Dependencies

Make sure your build configuration includes the necessary dependencies:

**Maven (`pom.xml`):**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>




