# Integration Platform

This Spring Boot project currently demonstrates the CRM-to-webhook boundary:

`CRM → save/update customer in PostgreSQL → create CustomerEvent → HTTP POST /webhook/customer → receive response`

## Run

Prerequisites: Java 21 and PostgreSQL. Supply connection values through environment variables:

```powershell
$env:POSTGRES_URL="jdbc:postgresql://localhost:5432/integration_platform"
$env:POSTGRES_USERNAME="postgres"
$env:POSTGRES_PASSWORD="your-password"
./mvnw.cmd spring-boot:run
```

The CRM endpoint is `POST /customer/createOrUpdate`; it saves or updates the customer, builds a `CustomerEvent`, and posts it to `/webhook/customer`.
