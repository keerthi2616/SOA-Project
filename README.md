# ParkGrid Solutions — Urban Mobility Microservices

A complete academic microservices project implementing the provided Continuous Evaluation Project rubric.

## Rubric coverage

| Rubric | Implementation |
|---|---|
| Problem Analysis & Requirements | `docs/01-requirements.md`, architecture diagram and use cases |
| Microservice Identification & Service Discovery | Parking, Booking, Billing, Auth, API Gateway + Eureka |
| JWT Authentication | Auth service issues JWT; Gateway and business services validate JWT and roles |
| API Gateway | Spring Cloud Gateway routes all client APIs using Eureka + load balancing |
| LinkedIn DTI Article | `docs/05-linkedin-dti-article.md` — ready-to-edit article draft |
| Inter-service communication | Booking → Billing and Booking → Parking through service discovery |
| Zero double-booking | Atomic DB update changes a slot from available → reserved only when available |
| Unit / Integration Tests | Test classes are included in Parking, Billing and Booking services |
| Docker / Cloud | Dockerfiles + `docker-compose.yml` included |

## Technology stack

- Java 21
- Spring Boot 4.0.8
- Spring Cloud 2025.1.3
- Spring Cloud Gateway Server WebFlux
- Netflix Eureka
- Spring Security + JWT
- Spring Data JPA
- H2 database for easy local/demo execution
- Maven
- Docker Compose

Spring Cloud 2025.1.3 is based on Spring Boot 4.0.8. See the official Spring Cloud release note and compatibility table before changing versions.

## Architecture

```text
                         +----------------------+
                         |      Web / Postman   |
                         +----------+-----------+
                                    |
                                    v
                         +----------------------+
                         |     API Gateway       |
                         |  :8080 / JWT / LB     |
                         +----------+-----------+
                                    |
              +---------------------+----------------------+
              |                     |                      |
              v                     v                      v
       +-------------+       +-------------+       +-------------+
       | Auth Service|       | Parking     |       | Booking     |
       |    :8081    |       | Service     |       | Service     |
       +------+------+       |    :8082    |       | :8083/:8086 |
              |              +------+------+       +------+------+ 
              |                     ^                     |
              |                     |                     | WebClient
              |                     |                     v
              |                     |              +-------------+
              |                     +--------------| Billing     |
              |                                    | Service     |
              |                                    | :8084/:8087 |
              |                                    +-------------+
              |
              +-----------------------+
                                      |
                              +-------v-------+
                              | Eureka Server |
                              |     :8761     |
                              +---------------+
```

## Default demo accounts

- USER: `user1` / `User@123`
- ADMIN: `admin` / `Admin@123`

Change these before any real deployment.

## Run in VS Code without Docker

1. Install JDK 21 and Maven.
2. Open this folder in VS Code.
3. Run `mvn clean install` from the root.
4. Start services in this order: Eureka → Auth → Parking → Billing → Booking → Gateway.
5. Use the gateway at `http://localhost:8080` for client requests.

## Run with Docker

```bash
docker compose up --build
```

The compose file starts two Booking instances and two Billing instances so the gateway can demonstrate load balancing through Eureka.

## Main gateway endpoints

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/parking/availability`
- `GET /api/parking/zones`
- `POST /api/parking/zones` (ADMIN)
- `POST /api/bookings`
- `GET /api/bookings/my`
- `GET /api/bookings/all` (ADMIN)
- `POST /api/billing/calculate`
- `GET /api/billing/revenue` (ADMIN)

See `docs/04-api-testing.md` for exact Postman requests.

## Rubric demonstration order

1. **Problem analysis:** explain the congestion problem, actors, functional/non-functional requirements and use cases from `docs/01-requirements.md`.
2. **Microservices:** show each service and responsibility; open Eureka and point out registrations.
3. **JWT:** login, copy token, call a protected API, then show an admin-only endpoint with the admin token.
4. **Gateway:** use only `localhost:8080` from Postman and explain `lb://` routes.
5. **Load balancing:** in Docker, show two Booking and two Billing registrations in Eureka. Multiple requests can be distributed among instances.
6. **Double-booking:** reserve one slot twice; the second attempt returns `409 Conflict` because the Parking DB update only succeeds when `available=true`.
7. **Inter-service communication:** show Booking logs and the resulting billing amount.
8. **Testing:** run `mvn test` and show both unit and Spring Boot integration tests.
9. **Deployment:** run `docker compose up --build` and show `docker compose ps`.
10. **DTI article:** edit `docs/05-linkedin-dti-article.md` with your screenshots and publish if required by the course.
