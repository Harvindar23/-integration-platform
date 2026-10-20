package com.harvindar.integration.CRM.Webhook;

import com.harvindar.integration.Webhook.Models.CustomerEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CustomerWebhookClient {

    private final RestClient restClient;

    public CustomerWebhookClient(
            RestClient.Builder builder,
            @Value("${integration.platform.webhook.base-url}") String webhookBaseUrl) {

        this.restClient = builder.baseUrl(webhookBaseUrl).build();
    }

    public void sendCustomerEvent(CustomerEvent event) {

        restClient.post()
                .uri("/webhook/customer")
                .body(event)
                .retrieve()
                .toBodilessEntity();
    }
}