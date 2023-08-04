package com.example.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor @AllArgsConstructor @Data
public class RoleDto {
    @Size(min = 3, max = 45, message = "Invalid fullName (3-45 characters)")
    private String name;
}
