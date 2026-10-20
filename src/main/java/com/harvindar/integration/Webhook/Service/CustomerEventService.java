package com.harvindar.integration.Webhook.Service;

import com.harvindar.integration.Webhook.Models.CustomerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Application-level processing of webhook events.
 *
 * This service is the application boundary for received webhook events at the
 * current CRM-to-webhook learning milestone.
 */
@Service
public class CustomerEventService {

    private static final Logger log = LoggerFactory.getLogger(CustomerEventService.class);

    public void process(CustomerEvent customerEvent) {

        log.info(
                "Processing customer event: eventId={}, eventType={}, eventVersion={}, customerId={}",
                customerEvent.getEventId(),
                customerEvent.getEventType(),
                customerEvent.getEventVersion(),
                customerEvent.getCustomer() != null
                        ? customerEvent.getCustomer().getCustomerId()
                        : null);

    }
}
