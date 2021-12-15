package com.example.product.feignconfig;

import com.example.product.model.WO.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "categoryFeignClient", url = "http://localhost:9004/api/v1/category/")
public interface CategoryFeignClient {
    @GetMapping("/get-by-id/{id}")
    Category getById(@PathVariable Long id);

    @GetMapping("/get-all")
    List<Category> getAll();
}
