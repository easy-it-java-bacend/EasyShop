package com.example.order.feignconfig;

import com.example.order.model.WO.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feignCustomerClient", url = "http://localhost:9001/api/v1/customer/")
public interface CustomerClient {
    @GetMapping("/get-by-id/{id}")
    Customer getById(@PathVariable Long id);
}
