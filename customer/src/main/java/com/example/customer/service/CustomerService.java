package com.example.customer.service;

import com.example.customer.model.request.RegisterCustomerRequest;
import com.example.customer.model.response.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerResponse create(RegisterCustomerRequest registerCustomerRequest);
    CustomerResponse getById(Long id);
}
