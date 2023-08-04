package com.example.Library.service;

import com.example.Library.dto.RoleDto;
import com.example.Library.model.Role;
import com.example.Library.model.User;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findById(Long id);
    Role newRole(RoleDto roleDto);
    String addUserToRoles(Long userId, Long roleId);
    Role updateRole(Long id, RoleDto roleDto);
    void deleteRole(Long id);
    List<Role> getAll();
    List<Role> searchRole(String keyword);
    User deleteUserFromRole(Long userId, Long roleId);
    List<User> getListUserBelongingRole(Long roleId);
}
