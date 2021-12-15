package com.example.category.service.impl;

import com.example.category.feignconfig.ProductFeignClient;
import com.example.category.model.WO.Product;
import com.example.category.model.entity.Category;
import com.example.category.model.request.CreateCategoryRequest;
import com.example.category.model.response.CategoryResponse;
import com.example.category.repository.CategoryRepository;
import com.example.category.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {
    final CategoryRepository categoryRepository;
    final ProductFeignClient productFeignClient;

    @Override
    public CategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        List<Product> products = productFeignClient.getAll();
        Category category = Category.builder()
                .name(createCategoryRequest.getName())
                .productIds(products.stream()
                        .filter(product -> createCategoryRequest.getProductIds()
                                .contains(product.getId()))
                        .map(product -> product.getId()).collect(Collectors.toList()))
                .build();
        categoryRepository.save(category);
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .productIds(category.getProductIds())
                .products(productFeignClient.getAll().stream()
                        .filter(product -> createCategoryRequest.getProductIds()
                                .contains(product.getId()))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public CategoryResponse getById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    return CategoryResponse.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .productIds(category.getProductIds())
                            .products(productFeignClient.getAll().stream()
                                    .filter(product -> category.getProductIds()
                                            .contains(product.getId()))
                                    .collect(Collectors.toList()))
                            .build();
                })
                .orElseThrow(() -> new RuntimeException("Category with id + " + id + " not found"));
    }

    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.getAllByIdNotNull()
                .map(categories -> categories.stream().map(category -> {
                    return CategoryResponse.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .productIds(category.getProductIds())
                            .products(productFeignClient.getAll().stream()
                                    .filter(product -> category.getProductIds()
                                            .contains(product.getId()))
                                    .collect(Collectors.toList()))
                            .build();
                }).collect(Collectors.toList())).orElseThrow(() -> new RuntimeException("No categories available"));
    }
}

