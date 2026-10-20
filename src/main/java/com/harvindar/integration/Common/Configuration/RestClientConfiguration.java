package com.harvindar.integration.Common.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Provides a {@link RestClient.Builder} bean for the application.
 *
 * Spring Boot 4 no longer auto-configures one, so it is created here once and
 * injected by HTTP-client components (e.g. the CRM webhook client).
 */
@Configuration
public class RestClientConfiguration {

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}