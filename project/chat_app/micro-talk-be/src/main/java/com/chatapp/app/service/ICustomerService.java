package com.chatapp.app.service;

import com.chatapp.app.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICustomerService {
    Page<Customer> getAllCustomers(Pageable pageable);
}
