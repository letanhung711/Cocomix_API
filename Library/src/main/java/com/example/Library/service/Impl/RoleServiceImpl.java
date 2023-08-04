package com.example.Library.service.Impl;

import com.example.Library.dto.RoleDto;
import com.example.Library.model.Role;
import com.example.Library.model.User;
import com.example.Library.repository.RoleRepository;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role;
    }

    @Override
    public Role newRole(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        return roleRepository.save(role);
    }

    @Override
    public String addUserToRoles(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if(user.isPresent() && role.isPresent()){
            Role role1 = role.get();
            User user1 = user.get();

            Collection<Role> roleUser = user1.getRoles();
            if(roleUser.contains(role1)) {
                return "The role has existed in this user";
            }
            roleUser.clear();
            roleUser.add(role1);
            user1.setRoles(roleUser);
            userRepository.save(user1);
        }
        return "Add a successful user role!";
    }

    @Override
    public Role updateRole(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).get();
        role.setName(roleDto.getName());
        Role updateRole = roleRepository.save(role);
        return updateRole;
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> searchRole(String keyword) {
        return roleRepository.findByName(keyword);
    }

    @Override
    public User deleteUserFromRole(Long userId, Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Collection<Role> userRole = user.getRoles();
        userRole.remove(role);
        user.setRoles(userRole);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getListUserBelongingRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(EntityNotFoundException::new);
        List<User> users = userRepository.findByRoles_Id(role.getId());
        return users;
    }
}
