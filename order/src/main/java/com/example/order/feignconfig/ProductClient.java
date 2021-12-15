package com.example.order.feignconfig;

import com.example.order.model.WO.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "feignProductClient", url = "http://localhost:9003/api/v1/product/")
public interface ProductClient {
    @GetMapping("/get-by-id/{id}")
    Product getById(@PathVariable Long id);

    @GetMapping("/get-all")
    List<Product> getAll();
}
