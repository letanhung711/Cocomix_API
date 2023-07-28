package com.example.Library.service;

import com.example.Library.dto.CategoryDto;
import com.example.Library.model.Category;
import com.example.Library.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);
    List<Category> getAllCategory();
    List<Product> getProductsByCategoryId(Long id);
    Category save(CategoryDto categoryDto);
    Category update(Long id, CategoryDto categoryDto);
    void delete(Long id);
}
