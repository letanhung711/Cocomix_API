package com.example.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data @NoArgsConstructor @AllArgsConstructor
public class MarketDto {
    @Size(min = 3, max = 45, message = "Invalid fullName (3-45 characters)")
    private String name;
    private Double price_increase;
    private Double old_price;
}
