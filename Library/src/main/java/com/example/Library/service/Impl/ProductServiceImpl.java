package com.example.Library.service.Impl;

import com.example.Library.dto.ProductDto;
import com.example.Library.model.Category;
import com.example.Library.model.Product;
import com.example.Library.repository.CategoryRepository;
import com.example.Library.repository.ProductRepository;
import com.example.Library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductInformation(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setNote(productDto.getNote());
        product.setCreate_time(convertToDate(productDto.getCreate_time()));
        product.setUpdate_time(convertToDate(productDto.getUpdate_time()));
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).get();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setNote(productDto.getNote());
        product.setCreate_time(convertToDate(productDto.getCreate_time()));
        product.setUpdate_time(convertToDate(productDto.getUpdate_time()));
        Product updateProduct = productRepository.save(product);
        return updateProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Timestamp convertToDate(String date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateObject = dateFormat.parse(date);
            Timestamp timestamp = new Timestamp(dateObject.getTime());
            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
