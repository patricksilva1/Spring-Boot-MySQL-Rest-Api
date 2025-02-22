# 🚀 Spring Boot, H2, JPA & RESTful API

A complete CRUD RESTful API built with **Spring Boot**, **H2 Database**, and **MVC Design Pattern**.

## 🏗 Architecture

![API Restful Architecture](https://raw.githubusercontent.com/patricksilva1/Spring-Boot-MySQL-Rest-Api/main/screenshot/APIRestful.png)

## 📋 Requirements

Ensure you have the following installed before proceeding:

- **Java** 1.8 or newer
- **Maven** 3.x

## 🚀 Setup Guide

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/patricksilva1/Spring-Boot-H2-Rest-Api.git
cd Spring-Boot-H2-Rest-Api
```

### 2️⃣ Start the Application

Run the application using Maven:

```bash
mvn spring-boot:run
```

Or execute the main class directly:

📂 /src/main/java/dev/patricksilva/crud/CrudApplication.java

### 3️⃣ Access the API
The application will be available at:

🔗 http://localhost:8080/

### 📜 H2 Database
The H2 Console allows you to explore the database:

🔗 http://localhost:8080/h2-console

Use the following credentials to log in:

```bash
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave blank)
```

### 📜 API Documentation
You can explore the API using Swagger:

🔗 http://localhost:8080/swagger-ui.html

Alternatively, use tools like Postman or Insomnia to test the endpoints.

## 🔥 REST API Endpoints

| Method   | Endpoint              | Description                |
|----------|-----------------------|----------------------------|
| `GET`    | `/api/products`       | Retrieve all products     |
| `GET`    | `/api/products/{id}`  | Retrieve a product by ID  |
| `POST`   | `/api/products`       | Create a new product      |
| `PUT`    | `/api/products/{id}`  | Update a product by ID    |
| `DELETE` | `/api/products/{id}`  | Delete a product by ID    |

### 📜 License
This project is licensed under the MIT License.