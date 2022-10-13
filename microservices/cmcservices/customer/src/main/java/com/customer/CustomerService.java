package com.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    ICustomerRepository customerRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Customer registerCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );
         if (fraudCheckResponse.getFraudster()) {
             throw new IllegalStateException("fraudster");
         }
        return customer;
    }
}
