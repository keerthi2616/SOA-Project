# ParkGrid — Problem Analysis and Requirement Specification

## Problem

Urban drivers lose time searching for parking. Commercial centers and public facilities also need a central way to manage capacity, reservations, occupancy and revenue.

## Objective

ParkGrid digitizes parking by providing real-time slot availability, advance reservations, automatic duration-based pricing and administrator analytics.

## Functional requirements

1. User registration/login with JWT authentication.
2. User can view parking zones and available slots.
3. User can reserve an available slot.
4. A reserved slot cannot be reserved by another concurrent request.
5. Booking stores vehicle/user/slot/time information.
6. Billing calculates the parking fee from duration and tariff.
7. Admin can create zones and slots.
8. Admin can view occupancy and revenue metrics.
9. Services register with Eureka.
10. API Gateway routes client requests.
11. Booking communicates with Parking and Billing services.
12. Tests validate important service behavior.

## Non-functional requirements

- Secure: JWT + role-based access.
- Scalable: stateless APIs and multiple Booking/Billing instances.
- Available: Eureka service discovery and gateway load balancing.
- Consistent booking: atomic availability update prevents double reservation at the slot level.
- Maintainable: independently deployable microservices.

## Actors

### USER
- Login/register
- View availability
- Create booking
- View own bookings

### ADMIN
- All user capabilities
- Manage zones/slots
- View occupancy
- View revenue trends

## Use-case summary

```text
USER ----------------> View Availability
USER ----------------> Reserve Slot ------> Parking Service
USER ----------------> View My Bookings
                           |
                           +---------------> Billing Service

ADMIN ---------------> Manage Zones/Slots
ADMIN ---------------> Occupancy Dashboard
ADMIN ---------------> Revenue Dashboard

Gateway -------------> Eureka ------------> service instances
```
