# Notes API

Simple REST service for managing Authors and Notes.

## Stack
- Java 17
- Spring Boot 3.3.4
- Spring Data JPA
- H2 in-memory database
- Swagger UI via springdoc-openapi

## Run
```bash
./mvnw spring-boot:run
```
(or `mvn spring-boot:run` if Maven is installed)

Swagger UI: http://localhost:8080/swagger-ui.html

H2 console: http://localhost:8080/h2-console  
JDBC URL: `jdbc:h2:mem:notesdb`

## API
### Authors
- `POST /authors` — body: `{ "name": "Ada Lovelace" }`
- `GET /authors`
- `GET /authors/{id}`

### Notes
- `POST /notes` — body: `{ "title": "My note", "content": "optional", "authorId": 1 }`
- `GET /notes`
- `GET /notes/{id}`
- `DELETE /notes/{id}`

## Build
```bash
mvn clean package
```
