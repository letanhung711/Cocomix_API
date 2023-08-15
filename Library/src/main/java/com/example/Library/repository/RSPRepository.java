package com.example.Library.repository;

import com.example.Library.model.RoleScreenPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RSPRepository extends JpaRepository<RoleScreenPermission, Long> {
    List<RoleScreenPermission> findAllById(Long id);
}
