package com.example.order.model.response;

import com.example.order.model.WO.Customer;
import com.example.order.model.WO.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Boolean isActive;
    Long customerId;
    List<Long> productIds;
    Customer customer;
    List<Product> products;
}
