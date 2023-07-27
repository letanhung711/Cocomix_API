package com.example.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor @AllArgsConstructor @Data
public class ProductDto {
    @Size(min = 3, max = 45, message = "Invalid fullName (3-45 characters)")
    private String name;
    private Double price;
    @Size(min = 3, max = 45, message = "Invalid fullName (3-45 characters)")
    private String quantity;
    @Size(min = 3, max = 45, message = "Invalid fullName (3-45 characters)")
    private String note;
    private String create_time;
    private String update_time;
}
