package com.example.Library.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Data
@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Collection<Order> orders;

    @OneToMany(mappedBy = "products")
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private User users;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Collection<MarketProduct> marketProducts;
}
