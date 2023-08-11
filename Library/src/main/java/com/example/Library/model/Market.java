package com.example.Library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "market")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmarket")
    private Long id;
    private String name;
    private Double price_increase;

    @OneToMany(mappedBy = "market")
    @JsonIgnore
    private Collection<MarketProduct> marketProducts;
}
