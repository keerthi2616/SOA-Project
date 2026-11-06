# Postman / REST Client Test Sequence

Base URL: `http://localhost:8080`

## 1. Login

POST `/api/auth/login`

```json
{
  "username": "user1",
  "password": "User@123"
}
```

Copy the returned token.

## 2. View zones

GET `/api/parking/zones`

Header:
`Authorization: Bearer <token>`

## 3. View availability

GET `/api/parking/availability`

## 4. Admin creates a zone

Login as `admin`, then:

POST `/api/parking/zones`

```json
{
  "name": "Tech Park A",
  "location": "Main Road",
  "capacity": 10
}
```

The service automatically creates 10 slots.

## 5. Create booking

POST `/api/bookings`

```json
{
  "slotId": 1,
  "startTime": "2026-09-20T18:00:00",
  "endTime": "2026-09-20T20:30:00"
}
```

The response contains the calculated amount.

## 6. Test double booking

Send the same booking twice from two clients. The first successful reserve changes the slot to unavailable. The other request receives `409 Conflict`.

## 7. Admin occupancy

GET `/api/parking/occupancy`

## 8. Admin revenue

GET `/api/billing/revenue`

## 9. Admin all bookings

GET `/api/bookings/all`
