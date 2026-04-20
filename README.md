# cs489-final-exam

Spring Boot backend scaffold for the Apartments Leasing Management domain.

## What's included

- Spring Boot 4 Gradle setup
- JPA entities with Lombok:
  - `Apartment`
  - `Address`
  - `Tenant`
  - `Lease`
- Basic JUnit test that validates Lombok builder usage on entities

## API endpoints

- `GET /api/apartments?state={state}` - list apartments for a state sorted by property name and size
- `GET /api/leases` - list all leases sorted by lease number, including apartment and tenant details
- `GET /api/leases/revenue?state={state}` - get total lease revenue for apartments in a given state
- `POST /api/apartments/{apartmentId}/tenants/{tenantId}/leases` - register a lease for an existing apartment and tenant

## API documentation

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Run tests

```zsh
./gradlew test
```

## Run the application

```zsh
./gradlew bootRun
```

Note: `src/main/resources/application.properties` is configured for PostgreSQL at `localhost:5432/cs489-final-exam`.

