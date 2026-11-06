# Microservice Identification and Service Discovery

## Services

### 1. Auth Service :8081
Owns users, passwords and JWT creation.

### 2. Parking Service :8082
Owns parking zones and slot availability. Its atomic reserve operation is the concurrency control point.

### 3. Booking Service :8083 / :8086
Owns reservation records. It calls Parking to reserve/release a slot and Billing to calculate the amount.

### 4. Billing Service :8084 / :8087
Calculates duration-based parking charges and maintains a simple in-memory revenue summary for the academic demo.

### 5. API Gateway :8080
Single entry point for client traffic. Routes to service names using Eureka and Spring Cloud LoadBalancer.

### 6. Eureka Server :8761
Service registry. All services except Eureka register themselves.

## Why this split?

Each business capability has a clear responsibility and can be scaled independently. Booking and Billing are intentionally deployed twice in Docker Compose to demonstrate load balancing.
