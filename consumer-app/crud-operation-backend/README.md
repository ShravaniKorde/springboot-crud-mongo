Here is a **clean, final, production-ready `README.md`** tailored for a project where **both the Dockerfile and Docker image are already pushed to GitHub**.
You can **copy–paste this directly** into your repo.

This version is **simple, beginner-friendly**, and **interview-ready**.

---

```md
# CRUD Operations – Spring Boot + MongoDB + Docker

A simple **CRUD REST API** built using **Spring Boot**, **MongoDB**, and **Docker**.  
The application is fully containerized and the Docker image is published to GitHub.

---

## 🛠 Tech Stack

- **Java 21**
- **Spring Boot**
- **Spring Data MongoDB**
- **MongoDB**
- **Gradle**
- **Docker & Docker Compose**
- **Swagger / OpenAPI**
- **Testcontainers (for tests)**

---

##  Docker Image

The Docker image for this application is available on **GitHub Container Registry**.

```

ghcr.io/<your-github-username>/crud-operations:latest

```

---

##  Project Structure

```

crud-operations/
├── src/
│   ├── main/
│   │   ├── java/com/example/crud_operations
│   │   └── resources/application.properties
│   └── test/
│       └── java/com/example/crud_operations
├── Dockerfile
├── docker-compose.yml
├── build.gradle
├── README.md
└── .gitignore

````

---

##  Run Using Docker (Recommended)

### Prerequisites
- Docker Desktop installed and running

### Steps

```bash
docker compose down -v
docker compose build --no-cache
docker compose up
````

### Application URLs

| Service         | URL                                                                            |
| --------------- | ------------------------------------------------------------------------------ |
| Spring Boot API | [http://localhost:8081](http://localhost:8081)                                 |
| Swagger UI      | [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html) |
| MongoDB         | localhost:27017                                                                |

---

##  Run Using Published Docker Image (No Build Required)

```bash
docker pull ghcr.io/<your-github-username>/crud-operations:latest
docker compose up
```

---

##  API Endpoints

| Method | Endpoint        | Description    |
| ------ | --------------- | -------------- |
| POST   | /api/users      | Create user    |
| GET    | /api/users      | Get all users  |
| GET    | /api/users/{id} | Get user by ID |
| PUT    | /api/users/{id} | Update user    |
| DELETE | /api/users/{id} | Delete user    |

---

##  Swagger Documentation

Swagger UI is available at:

```
http://localhost:8081/swagger-ui.html
```

Use it to explore and test APIs interactively.

---

##  Testing

This project uses **Testcontainers**, so MongoDB is started automatically during tests.

### Run tests

```bash
./gradlew clean test
```

✔ No local MongoDB required
✔ CI/CD friendly

---

##  Configuration

### MongoDB Connection (Docker)

```properties
spring.mongodb.uri=mongodb://mongodb:27017/cruddb
server.port=8081
```

MongoDB runs as a separate container and is accessed via Docker Compose networking.


---

## 📦 Build JAR Locally

```bash
./gradlew clean build
```

Generated JAR:

```
build/libs/*.jar
```

