package com.example.product.service;

import com.example.product.model.request.CreateProductRequest;
import com.example.product.model.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponse create(CreateProductRequest createProductRequest);
    ProductResponse getOneById(Long id);
    List<ProductResponse> getAll();
}
