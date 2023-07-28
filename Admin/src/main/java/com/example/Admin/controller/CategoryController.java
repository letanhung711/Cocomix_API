package com.example.Admin.controller;

import com.example.Library.dto.CategoryDto;
import com.example.Library.model.Category;
import com.example.Library.model.Product;
import com.example.Library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}/product")
    public ResponseEntity<List<Product>> getProductListByCategory(@PathVariable("categoryId") Long id) {
        List<Product> products = categoryService.getProductsByCategoryId(id);
        return ResponseEntity.ok(products);
    }

    @PostMapping("")
    public ResponseEntity<Category> newCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.save(categoryDto);
        if(category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
        Category category = categoryService.update(id, categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        return  category.map(category1 -> {
            categoryService.delete(id);
            return ResponseEntity.ok("Delete category successful!");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Delete category fail!"));
    }
}
