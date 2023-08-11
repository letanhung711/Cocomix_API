package com.example.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

import javax.validation.constraints.Size;
import java.sql.Blob;

@Data @AllArgsConstructor @NoArgsConstructor
public class ScreenDto {
    @Size(min = 3, max = 45, message = "Invalid fullName (3-45 characters)")
    private String name;
    private String images;
    @Size(min = 3, max = 500, message = "Invalid fullName (3-45 characters)")
    private String description;
}
