package com.example.Admin.controller;

import com.example.Library.model.Category;
import com.example.Library.model.Product;
import com.example.Library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = productService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}/product")
    public ResponseEntity<List<Product>> getProductListByCategory(@PathVariable("categoryId") Long id) {
        List<Product> products = productService.getProductsByCategoryId(id);
        return ResponseEntity.ok(products);
    }
}
