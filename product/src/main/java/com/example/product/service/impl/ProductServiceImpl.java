package com.example.product.service.impl;

import com.example.product.feignconfig.CategoryFeignClient;
import com.example.product.model.WO.Category;
import com.example.product.model.entity.Product;
import com.example.product.model.request.CreateProductRequest;
import com.example.product.model.response.ProductResponse;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;
    final CategoryFeignClient categoryFeignClient;

    @Override
    public ProductResponse create(CreateProductRequest createProductRequest) {
        log.info("Creating product");
        List<Category> categories = categoryFeignClient.getAll();
        Product product = Product.builder()
                .name(createProductRequest.getName())
                .price(createProductRequest.getPrice())
                .discount(createProductRequest.getDiscount())
                .categoryIds(categories.stream()
                        .filter(category -> createProductRequest.getCategoryIds().contains(category.getId()))
                        .map(category -> category.getId())
                        .collect(Collectors.toList()))
                .build();

        productRepository.save(product);

        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .categories(categoryFeignClient.getAll().stream()
                        .filter(category -> createProductRequest.getCategoryIds()
                                .contains(category.getId()))
                        .collect(Collectors.toList()))
                .build();
        return productResponse;
    }

    @Override
    public ProductResponse getOneById(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    ProductResponse productResponse = ProductResponse.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .discount(product.getDiscount())
                            .categoryIds(product.getCategoryIds())
                            .categories(categoryFeignClient.getAll().stream()
                                    .filter(category -> product.getCategoryIds().contains(category.getId()))
                                    .collect(Collectors.toList()))
                            .build();
                    return productResponse;
                })
                .orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found"));
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.getAllByIdNotNull()
                .map(products -> products.stream().map(product -> {
                    return ProductResponse.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .discount(product.getDiscount())
                            .categoryIds(product.getCategoryIds())
                            .categories(categoryFeignClient.getAll().stream()
                                    .filter(category -> product.getCategoryIds().contains(category.getId()))
                                    .collect(Collectors.toList()))
                            .build();
                }).collect(Collectors.toList()))
                .orElseThrow(() -> new RuntimeException("No products available"));
    }
}
