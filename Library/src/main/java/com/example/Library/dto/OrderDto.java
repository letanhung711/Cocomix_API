package com.example.Library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Data @AllArgsConstructor @NoArgsConstructor
public class OrderDto {
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private Date ngaydat;
    @Size(min = 3, max = 45, message = "Invalid fullName (3-45 characters)")
    private String name;
    @Size(min = 3, max = 45, message = "Invalid fullName (3-45 characters)")
    private String address;
    @Size(min = 3, max = 45, message = "Invalid fullName (3-45 characters)")
    private String phonenumber;
    @Size(min = 3, max = 500, message = "Invalid fullName (3-500 characters)")
    private String note;
    private String totalproducts;
    private String quantity;
    private String price;
    private String totalprice;
    private Timestamp create_time;
}
