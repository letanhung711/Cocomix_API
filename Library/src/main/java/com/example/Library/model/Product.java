package com.example.Library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@NoArgsConstructor @AllArgsConstructor @Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private Double price;
    private String quantity;
    private String note;
    private Timestamp create_time;
    private Timestamp update_time;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Collection<Category> categories;
}
