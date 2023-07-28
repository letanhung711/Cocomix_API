package com.example.Library.model;

import javax.persistence.*;

@Entity
@Table(name = "product_category")
public class Product_Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
