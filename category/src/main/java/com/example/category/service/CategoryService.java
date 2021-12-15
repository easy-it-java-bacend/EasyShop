package com.example.category.service;

import com.example.category.model.request.CreateCategoryRequest;
import com.example.category.model.response.CategoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryResponse create(CreateCategoryRequest createCategoryRequest);
    CategoryResponse getById(Long id);
    List<CategoryResponse> getAll();
}
