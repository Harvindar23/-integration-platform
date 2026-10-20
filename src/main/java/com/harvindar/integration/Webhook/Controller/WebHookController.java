package com.harvindar.integration.Webhook.Controller;

import com.harvindar.integration.Webhook.Models.CustomerEvent;
import com.harvindar.integration.Webhook.Service.CustomerEventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebHookController {

    private final CustomerEventService customerEventService;

    public WebHookController(CustomerEventService customerEventService) {
        this.customerEventService = customerEventService;
    }

    @PostMapping("/customer")
    public ResponseEntity<String> receiveCustomerEvent(
            @Valid @RequestBody CustomerEvent customerEvent) {

        customerEventService.process(customerEvent);

        return ResponseEntity.accepted().body("Webhook accepted");
    }
}
