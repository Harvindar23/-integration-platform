package com.harvindar.integration.CRM.Models.Requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerInfo {
    String name;
    String email;
    String phone;
}
