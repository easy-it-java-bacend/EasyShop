package com.example.order.service;

import com.example.order.model.request.CreateOrderRequest;
import com.example.order.model.response.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderResponse create(CreateOrderRequest createOrderRequest);
    OrderResponse getById(Long id);
}
