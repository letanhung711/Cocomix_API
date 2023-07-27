package com.example.Admin.controller;

import com.example.Library.dto.ProductDto;
import com.example.Library.model.Product;
import com.example.Library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Product> newProduct(@RequestBody ProductDto productDto) {
        Product product = productService.save(productDto);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getProduct(@RequestParam(value = "query", required = false) String keyword) {
        List<Product> products = productService.getAllProduct();
        if(keyword != null && !keyword.isEmpty()) {
            products = products.stream().filter(product ->
                    product.getName().contains(keyword)).collect(Collectors.toList());
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> getProductId(@PathVariable("id") Long id) {
        Optional<Product> product = productService.getProductInformation(id);
        if(product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,
                                                 @RequestBody ProductDto productDto) {
        Product product = productService.updateProduct(id, productDto);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(product1 -> {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Delete product successful!");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Delete product fail!"));
    }
}
