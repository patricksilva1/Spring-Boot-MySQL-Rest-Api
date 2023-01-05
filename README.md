# 2023 -  Spring Boot, MySQL, JPA, Restful API tutorial

Building a CRUD Restful API using Spring Boot with MySQL Database and MVC Design Pattern.

## Requirements

1. Java - 1.8.x or newer
2. Maven - 3.x.x
3. MySQL - 5.x.x

## Steps to Setup

### 1. Clone the repository

> git clone https://github.com/patricksilva1/Spring-Boot-MySQL-Rest-Api.git

### 2. Create MySQL Database

> CREATE DATABASE crud;

### 3. Change MySQL Username and Password as per your installation

* Open `/crud/src/main/resources/application.properties`
* Change `spring.datasource.username` and `spring.datasource.password` as per your MySQL Credentials.

### 4. Start the application via Maven package or Start it using Spring Boot Application as default:
 `/crud/src/main/java/dev/patricksilva/crud/CrudApplication.java`

### 5. The App will start running at http://localhost:9010/

___

## Explore HTTP Methods:

| Http Methods | URL                                     |
| ------------ | --------------------------------------- |
| GET          | http://localhost:9010/api/products      |
| GET/{id}     | http://localhost:9010/api/products/{id} |
| POST         | http://localhost:9010/api/products      |
| PUT          | http://localhost:9010/api/products/{id} |
| DEL          | http://localhost:9010/api/products/{id} |

You can use them using a REST client such as Postman, Insomnia, etc.

Be careful to use the correct Id and remove the `{id}`. For example: 
> Put - http://localhost:9010/api/products/1