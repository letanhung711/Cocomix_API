package com.example.Library.service.Impl;

import com.example.Library.dto.UserDto;
import com.example.Library.model.User;
import com.example.Library.repository.RoleRepository;
import com.example.Library.repository.UserRepository;
import com.example.Library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername());
        if(user == null)
            return null;
        String password = userDto.getPassword();
        String passEncode = user.getPassword();
        boolean rs = passwordEncoder.matches(password, passEncode);
        if(rs == true){
            return user;
        }else {
            return null;
        }
    }

    @Override
    public User savedUser(UserDto userDto) {
        Date dateNow = new Date();

        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setBirthdate(userDto.getBirthdate());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCreate_time(dateNow.toString());
        user.setRoles(Arrays.asList(roleRepository.findByName("admin")));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UserDto userDto) {
        User user1 = userRepository.findById(id).get();
        user1.setFullName(userDto.getFullName());
        user1.setBirthdate(userDto.getBirthdate());
        user1.setPhoneNumber(userDto.getPhoneNumber());
        user1.setEmail(userDto.getEmail());
        user1.setUsername(userDto.getUsername());
        user1.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User updateUser=userRepository.save(user1);
        return updateUser;
    }

    @Override
    public List<User> searchUser(String keyword) {
        return userRepository.findByEmailContainingOrUsernameContaining(keyword, keyword);
    }

    @Override
    public User updatePass(Long id, String password) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            User user1 = user.get();
            user1.setPassword(passwordEncoder.encode(password));
            return userRepository.save(user1);
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
