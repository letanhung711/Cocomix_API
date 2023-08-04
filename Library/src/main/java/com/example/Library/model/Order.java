package com.example.Library.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    private Date ngaydat;
    private String name;
    private String address;
    private String phoneNumber;
    private String note;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String totalProducts;

    @OneToMany(mappedBy = "orders")
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product products;
}
