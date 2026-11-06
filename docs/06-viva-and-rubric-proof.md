# Viva / Rubric Demonstration Checklist

## Rubric 1 — Problem Analysis and Requirement Specification
Show `docs/01-requirements.md`, use cases and architecture diagram.

## Rubric 2 — Microservice Identification and Service Discovery
Open Eureka at `http://localhost:8761` and show AUTH-SERVICE, PARKING-SERVICE, BOOKING-SERVICE and BILLING-SERVICE registrations. Explain why Booking and Billing have two instances in Docker.

## Rubric 3 — JWT Authentication
Show login response containing a token. Call an endpoint with no token and show rejection. Login as admin and demonstrate an admin-only endpoint.

## Rubric 4 — API Gateway
Send all client requests to port 8080 rather than directly to service ports. Explain that routes use `lb://SERVICE-NAME` and Eureka supplies service instances.

## Rubric 5 — LinkedIn DTI Article
Use `docs/05-linkedin-dti-article.md` as the article draft, add your screenshots/architecture image and publish it on LinkedIn if your course requires an actual post.

## Strong extra points

- Show two Booking instances in Eureka.
- Show two Billing instances in Eureka.
- Demonstrate two simultaneous booking requests for one slot.
- Show 409 Conflict for the losing request.
- Show Booking → Billing communication in logs.
- Run unit tests with `mvn test`.
- Show Docker containers with `docker compose ps`.
