package com.example.Library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class User_OrderDto {
    private Long iduser;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private Date ngaydat;
    private String address;
    private Long idorder;
}
