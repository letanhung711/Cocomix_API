package com.example.Library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "market_has_product")
@Data @NoArgsConstructor @AllArgsConstructor
public class MarketProduct {
    @EmbeddedId
    private ProductMarketId productMarketId;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("marketId")
    @JoinColumn(name = "idmarket")
    private Market market;

    private Double old_price;
    private Double price_increase;
}
