package com.example.Library.service;

import com.example.Library.dto.UserDto;
import com.example.Library.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByUsername(UserDto userDto);
    User savedUser(UserDto userDto);
    Optional<User> findById(Long id);
    void deleteUser(Long id);
    List<User> getAllUser();
    User updateUser(Long id, UserDto userDto);
    List<User> searchUser(String keyword);
    User updatePass(Long id, String password);
    User findByEmail(String email);
}
