package com.example.Admin.controller;

import com.example.Library.dto.RoleDto;
import com.example.Library.model.Role;
import com.example.Library.model.User;
import com.example.Library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("")
    public ResponseEntity<String> newRole(@RequestBody RoleDto roleDto) {
        Role role = roleService.newRole(roleDto);
        if(role == null) {
            return ResponseEntity.badRequest().body("Create new role fail!");
        }
        return ResponseEntity.ok("Create new role successful!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRole(@PathVariable("id") Long id, @RequestBody RoleDto roleDto) {
        Optional<Role> role = roleService.findById(id);
        if(role.isEmpty()) {
            return ResponseEntity.badRequest().body("Can not find the role!");
        }
        Role update = roleService.updateRole(id, roleDto);
        return ResponseEntity.ok("Update role successful!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") Long id) {
        Optional<Role> role = roleService.findById(id);
        return role.map(role1 -> {
            roleService.deleteRole(id);
            return ResponseEntity.ok("Delete role successful!");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Can not find the role!"));
    }

    @GetMapping("")
    public ResponseEntity<List<Role>> getRole(@RequestParam(value = "query", required = false) String keyword) {
        List<Role> roles = roleService.getAll();
        if(keyword != null && !keyword.isEmpty()) {
            roles = roleService.searchRole(keyword);
        }
        return ResponseEntity.ok(roles);
    }

    @DeleteMapping("/{roleId}/user/{userId}")
    public ResponseEntity<User> deleteUserFromRole(@PathVariable("roleId") Long roleId,
                                                     @PathVariable("userId") Long userId) {
        User user = roleService.deleteUserFromRole(userId, roleId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{roleId}/user")
    public ResponseEntity<List<User>> getAllUserByRole(@PathVariable("roleId") Long roleId) {
        List<User> user = roleService.getListUserBelongingRole(roleId);
        if(user.isEmpty()) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.ok(user);
    }
}
