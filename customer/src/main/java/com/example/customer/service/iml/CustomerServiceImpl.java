package com.example.customer.service.iml;

import com.example.customer.model.entity.Customer;
import com.example.customer.model.request.RegisterCustomerRequest;
import com.example.customer.model.response.CustomerResponse;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    final CustomerRepository customerRepository;
    
    @Override
    public CustomerResponse create(RegisterCustomerRequest registerCustomerRequest) {
        log.info("Registering customer");
        Customer customer = Customer.builder()
                .firstName(registerCustomerRequest.getFirstName())
                .lastName(registerCustomerRequest.getLastName())
                .phoneNumber(registerCustomerRequest.getPhoneNumber())
                .email(registerCustomerRequest.getEmail())
                .build();
        customerRepository.save(customer);
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .email(customer.getEmail())
                .build();
    }

    @Override
    public CustomerResponse getById(Long id) {
        return customerRepository.findById(id)
                .map(customer -> {
                    return CustomerResponse.builder()
                            .id(customer.getId())
                            .firstName(customer.getFirstName())
                            .lastName(customer.getLastName())
                            .phoneNumber(customer.getPhoneNumber())
                            .email(customer.getEmail())
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
