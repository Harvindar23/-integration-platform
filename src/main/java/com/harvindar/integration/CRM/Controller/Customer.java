package com.harvindar.integration.CRM.Controller;

import com.harvindar.integration.CRM.Models.Requests.CustomerInfo;
import com.harvindar.integration.CRM.Service.CustomerInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestMapping("/customer")
public class Customer {
    private final CustomerInfoService customerInfoService;

    public  Customer(CustomerInfoService customerInfoService)
        {
            this.customerInfoService = customerInfoService;
        }

    @PostMapping ("/createOrUpdate")
    public ResponseEntity<String>  createOrUpdateCustomerInfo(@RequestBody CustomerInfo customerInfo)
    {
        ResponseEntity<String> response =  customerInfoService.createOrUpdateCustomerInfo(customerInfo);
        return response;
    }
}
