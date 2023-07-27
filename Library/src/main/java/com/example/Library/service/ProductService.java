package com.example.Library.service;

import com.example.Library.dto.ProductDto;
import com.example.Library.model.Category;
import com.example.Library.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
    List<Product> getAllProduct();
    Optional<Product> getProductInformation(Long id);
    Product save(ProductDto productDto);
    Product updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
    List<Category> getAllCategory();
    List<Product> getProductsByCategoryId(Long id);
}
