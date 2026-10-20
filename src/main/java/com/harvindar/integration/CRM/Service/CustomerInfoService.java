package com.harvindar.integration.CRM.Service;

import com.harvindar.integration.CRM.Data.CustomerInfoData;
import com.harvindar.integration.CRM.Models.Requests.CustomerInfo;
import com.harvindar.integration.CRM.Repository.CustomerInfoRepository;
import com.harvindar.integration.CRM.Webhook.CustomerWebhookClient;
import com.harvindar.integration.Webhook.Models.Customer;
import com.harvindar.integration.Webhook.Models.CustomerEvent;
import com.harvindar.integration.Webhook.Models.Enum.CustomerEventType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerInfoService {

    private final CustomerInfoRepository repository;
    private final CustomerWebhookClient webhookClient;

    public CustomerInfoService(
            CustomerInfoRepository repository,
            CustomerWebhookClient webhookClient) {

        this.repository = repository;
        this.webhookClient = webhookClient;
    }

    public ResponseEntity<String> createOrUpdateCustomerInfo(CustomerInfo customerInfo) {

        if (Objects.isNull(customerInfo.getEmail())) {
            return new ResponseEntity<>(
                    "Email is necessary to provide",
                    HttpStatus.BAD_REQUEST);
        }

        Optional<CustomerInfoData> customerInfoData = repository.findByEmail(customerInfo.getEmail());

        CustomerInfoData savedCustomer;
        CustomerEventType eventType;

        if (customerInfoData.isPresent()) {

            if (isAlreadySame(customerInfoData.get(), customerInfo)) {
                return new ResponseEntity<>(
                        "The Provided info is same as existing",
                        HttpStatus.BAD_REQUEST);
            }

            CustomerInfoData updatedCustomer = createOrUpdate(customerInfoData.get(), customerInfo);

            savedCustomer = repository.save(updatedCustomer);
            eventType = CustomerEventType.UPDATE;

        } else {

            CustomerInfoData newCustomer = createOrUpdate(null, customerInfo);

            savedCustomer = repository.save(newCustomer);
            eventType = CustomerEventType.CREATE;
        }

        sendCustomerEvent(savedCustomer, eventType);

        return new ResponseEntity<>(
                "Operation Successful",
                HttpStatus.OK);
    }

    public boolean isAlreadySame(
            CustomerInfoData customerInfoData,
            CustomerInfo customerInfo) {

        if (Objects.isNull(customerInfoData)) {
            return false;
        }

        return Objects.equals(
                customerInfoData.getName(),
                customerInfo.getName())
                && Objects.equals(
                customerInfoData.getEmail(),
                customerInfo.getEmail())
                && Objects.equals(
                customerInfoData.getPhone(),
                customerInfo.getPhone());
    }

    public CustomerInfoData createOrUpdate(
            CustomerInfoData customerInfoData,
            CustomerInfo customerInfo) {

        if (Objects.isNull(customerInfoData)) {

            return CustomerInfoData.builder()
                    .email(customerInfo.getEmail())
                    .phone(customerInfo.getPhone())
                    .name(customerInfo.getName())
                    .build();
        }

        if (Objects.nonNull(customerInfo.getName())) {
            customerInfoData.setName(customerInfo.getName());
        }

        if (Objects.nonNull(customerInfo.getPhone())) {
            customerInfoData.setPhone(customerInfo.getPhone());
        }

        return customerInfoData;
    }

    private void sendCustomerEvent(
            CustomerInfoData customerInfoData,
            CustomerEventType eventType) {

        Customer customer = Customer.builder()
                .customerId(customerInfoData.getId())
                .name(customerInfoData.getName())
                .email(customerInfoData.getEmail())
                .phone(customerInfoData.getPhone())
                .build();

        CustomerEvent event = new CustomerEvent();

        event.setEventId(UUID.randomUUID());
        event.setEventType(eventType);
        event.setEventVersion(1);
        event.setOccurredAt(Instant.now());
        event.setCustomer(customer);

        webhookClient.sendCustomerEvent(event);
    }
}