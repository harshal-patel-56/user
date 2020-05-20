package com.harshal.todo.user.controllers;

import com.harshal.todo.user.domain.User;
import com.harshal.todo.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam int pageNo, @RequestParam int userCount) {
        return userService.getAllUsers(pageNo, userCount);
    }

    @GetMapping("/users/{username}")
    public User getUserById(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/")
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }

    @PutMapping("/users/{username}")
    public String updateUser(@RequestBody User updateUser) {
        return userService.updateUser(updateUser);
    }

}
