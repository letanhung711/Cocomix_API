package com.example.Library.service.Impl;

import com.example.Library.dto.CategoryDto;
import com.example.Library.model.Category;
import com.example.Library.model.Product;
import com.example.Library.repository.CategoryRepository;
import com.example.Library.repository.ProductRepository;
import com.example.Library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategoryId(Long id) {
        return productRepository.findByCategories_Id(id);
    }

    @Override
    public Category save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).get();
        category.setName(categoryDto.getName());
        Category update = categoryRepository.save(category);
        return update;
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
