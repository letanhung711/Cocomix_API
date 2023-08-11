package com.example.Library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class ProductMarketId implements Serializable {
    private Long productId;
    private Long marketId;
}
