# Targhee Sports Rental

1. [Introduction](#introduction)
2. [Features](#features)
3. [Architecture](#architecture)
4. [Setup](#setup)
   - [Prerequisites](#prerequisites)
   - [Installation](#installation)
   - [Database Setup](#database-setup)
5. [Usage](#usage)
   - [Running the Application](#running-the-application)
6. [Example Scenarios](#example-scenarios)
7. [Tests](#tests)
8. [Dependencies](#dependencies)


## Overview

The Targhee Sports Rental application is designed to manage sports equipment rentals. A user can search/retrieve products from the database, add to cart and checkout orders. An admin user can add, delete and update products as well as add, create and update employees.

## Features

- **src/main/java**: Contains the main application code, including entity classes and DAOs.
- **src/main/resources**: Configuration files and other resources.
- **User Authentication**: Students can authenticate using their email and password.
- **Course Management**: Students can view available courses and register for them.
- **Data Persistence**: Utilizes MySQL database for storing product, user and order information.
- **src/test/java**: Contains integration tests for DAOs.
    - `OrderDAOTest`: Tests the `OrderDAO` for retrieving `Order` entities.
    - `OrderDetailsDAOTest`: Tests the `OrderDetailsDAO` for retrieving `OrderDetail` entities.
    - `ProductDAOTest`: Tests the `ProductDAO` for retrieving `Product` entities.

 ## Architecture

The project follows a MVC pattern:

Presentation Layer
  -> Views
  -> ViewModels  
  -> Controllers 

Service Layer
  -> Includes business logic
  -> Uses data access interfaces

Data Access Layer (DAOs)
  -> Contracts (interfaces) for persistent storage
  -> Interface implementations

Entities
  -> POCO/ POJO that represent data

- **Presentation Layer**: CLI interface (`Main.java`) for user interaction.
- **Service Layer**: Business logic encapsulated in `StudentService.java` and `CourseService.java`.
- **Data Access Layer**: Uses Hibernate for interacting with the MySQL database (`Student.java`, `Course.java`).

## Prerequisites

- Java 17 or higher
- Maven or Gradle (for dependency management)
- A relational database (e.g., H2, PostgreSQL) configured in your application properties

## Dependencies

Make sure build configuration includes the necessary dependencies:

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




