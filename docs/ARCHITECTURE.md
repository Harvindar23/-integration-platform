# Architecture

The current learning milestone contains one Spring Boot application with a CRM module and a webhook module.

`CustomerInfoService` saves or updates a customer through JPA, creates a `CustomerEvent`, and delegates HTTP delivery to `CustomerWebhookClient`. The client posts the event to `WebHookController` at `/webhook/customer`. The controller validates and receives the event, then returns an HTTP response.
