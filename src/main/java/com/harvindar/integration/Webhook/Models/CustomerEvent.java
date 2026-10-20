package com.harvindar.integration.Webhook.Models;


import com.harvindar.integration.Webhook.Models.Enum.CustomerEventType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CustomerEvent {
    @NotNull
    private UUID eventId;

    @NotNull
    private CustomerEventType eventType;

    @NotNull
    @Min(1)
    private Integer eventVersion;

    @NotNull
    private Instant occurredAt;

    @NotNull
    @Valid
    private Customer customer;
}
