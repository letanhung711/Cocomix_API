package com.example.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDto {
    @Size(min = 3, max = 10, message = "Invalid fullName (3-10 characters)")
    private String fullName;
    private String birthdate;
    @Size(message = "Invalid phoneNumber (10 characters)")
    private String phoneNumber;
    private String email;
    private String username;
    @Size(min = 5, max = 15, message = "Invalid firstname (5-15 characters)")
    private String password;
    private String create_time;
}
