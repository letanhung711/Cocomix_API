package com.example.Library.repository;

import com.example.Library.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findByCategories_Id(Long id);
    boolean existsByName(String name);
}
