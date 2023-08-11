package com.example.Library.repository;

import com.example.Library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByEmailContainingOrUsernameContaining(String keyword, String keyword1);
    User findByEmail(String email);
    List<User> findByRoles_Id(Long id);
    User findByFullName(String name);
    boolean existsByfullName(String name);
}
