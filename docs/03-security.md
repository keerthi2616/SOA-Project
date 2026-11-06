# JWT Security Design

1. User sends username/password to Auth Service.
2. Auth Service verifies BCrypt password.
3. Auth Service creates a signed HMAC JWT containing username and role.
4. Client sends `Authorization: Bearer <token>` to the API Gateway.
5. Gateway validates the token before forwarding protected requests.
6. Downstream business services validate the token again, so security does not depend only on the gateway.
7. Admin-only endpoints use role `ADMIN`.
8. User endpoints accept role `USER` or `ADMIN` where appropriate.

The demo uses a shared HMAC secret through the `JWT_SECRET` environment variable. For production, use a secret manager or an asymmetric key/central identity provider.
