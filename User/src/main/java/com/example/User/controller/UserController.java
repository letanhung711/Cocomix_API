package com.example.User.controller;

import com.example.Library.dto.UserDto;
import com.example.Library.model.User;
import com.example.Library.service.CacheService;
import com.example.Library.service.RoleService;
import com.example.Library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        User user = userService.savedUser(userDto);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password) {
        User user = userService.findByUsername(username, password);
        if(user == null){
            return ResponseEntity.badRequest().body("Login fail!!!");
        }
        return ResponseEntity.ok("Login successful!");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUserInformation(@PathVariable("userId") Long id) {
        Optional<User> user = userService.findById(id);
        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getUser(@RequestParam(value = "query", required = false) String keyword){
        List<User> users = userService.getAllUser();
        if(keyword != null && !keyword.isEmpty()) {
            users = userService.searchUser(keyword);
        }
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "userId") Long id){
        Optional<User> user=userService.findById(id);
        return user.map(user1 -> {
            userService.deleteUser(id);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "userId") Long id,
                                           @RequestBody UserDto userDto) {
        User user = userService.updateUser(id, userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) {
        User user = userService.findByEmail(email);
        if(user == null) {
            return ResponseEntity.badRequest().body("User email doesn't exist!");
        }
        return ResponseEntity.ok(cacheService.generateAndCacheToken(user.getEmail()));
    }

    @PostMapping("/forgot-password/{token}")
    public ResponseEntity<String> updatePass(@PathVariable(value = "token") String token,
                                             @RequestParam("newPass") String newPass) {
        String email = cacheService.getEmailToken(token);
        if(email == null) {
            return ResponseEntity.badRequest().body("User email doesn't exist!");
        }
        User user = userService.findByEmail(email);
        User user1 = userService.updatePass(user.getId(), newPass);
        cacheService.removeToken(token);
        return ResponseEntity.ok("Update password successful!");
    }

    @PostMapping("/encrypt-password")
    public ResponseEntity<String> encryptPassword(@RequestParam("password") String password) {
        return ResponseEntity.ok(userService.encryptPassword(password));
    }

    @PostMapping("/{userId}/role/{roleId}")
    public ResponseEntity<String> addAdminToRole(@PathVariable("userId") Long userId,
                                                 @PathVariable("roleId") Long roleId) {
        String user = roleService.addUserToRoles(userId, roleId);
        return ResponseEntity.ok(user);
    }
}
