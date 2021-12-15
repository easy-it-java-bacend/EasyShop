package com.example.order.service.impl;

import com.example.order.feignconfig.CustomerClient;
import com.example.order.feignconfig.ProductClient;
import com.example.order.model.WO.Customer;
import com.example.order.model.WO.Product;
import com.example.order.model.entity.Order;
import com.example.order.model.request.CreateOrderRequest;
import com.example.order.model.response.OrderResponse;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {
    final OrderRepository orderRepository;
    final CustomerClient customerClient;
    final ProductClient productClient;


    @Override
    public OrderResponse create(CreateOrderRequest createOrderRequest) {
        List<Product> products = productClient.getAll();
        Customer customer = customerClient.getById(createOrderRequest.getCustomerId());
        Order order = Order.builder()
                .endDate(LocalDateTime.now().plusDays(12))
                .isActive(true)
                .customerId(customer.getId())
                .productIds(products.stream()
                        .filter(product -> createOrderRequest.getProductIds().contains(product.getId()))
                        .map(product1 -> product1.getId())
                        .collect(Collectors.toList()))
                .build();
        orderRepository.save(order);
        return OrderResponse.builder()
                .id(order.getId())
                .startDate(order.getStartDate())
                .endDate(order.getStartDate().plusDays(12))
                .isActive(order.getIsActive())
                .customerId(order.getCustomerId())
                .productIds(order.getProductIds())
                .products(products.stream()
                        .filter(product -> createOrderRequest.getProductIds()
                                .contains(product.getId()))
                        .collect(Collectors.toList()))
                .customer(customer)
                .build();
    }

    @Override
    public OrderResponse getById(Long id) {
        List<Product> products = productClient.getAll();
        Customer customer = customerClient.getById(orderRepository.getCustomerId(id));
        return orderRepository.findById(id)
                .map(order -> {
                    return OrderResponse.builder()
                            .id(order.getId())
                            .isActive(order.getIsActive())
                            .customerId(order.getCustomerId())
                            .productIds(order.getProductIds())
                            .products(products.stream()
                                    .filter(product -> order.getProductIds().contains(product.getId()))
                                    .collect(Collectors.toList()))
                            .customer(customer)
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Order by id: + " + id + " not found"));
    }
}
