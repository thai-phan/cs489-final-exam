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

## Run tests

```zsh
./gradlew test
```

## Run the application

```zsh
./gradlew bootRun
```

Note: `src/main/resources/application.properties` is configured for PostgreSQL at `localhost:5432/cs489-final-project`.

