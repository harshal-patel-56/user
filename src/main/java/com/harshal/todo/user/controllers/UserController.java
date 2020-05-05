package com.harshal.todo.user.controllers;

import com.harshal.todo.user.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    static ArrayList<User> users = new ArrayList<>();
    static {
        users.add(new User(1L, "jon_doe", "password1", "Jon", "Doe",
                "jondoe@gmail.com"));
        users.add(new User(2L, "doe_smith", "password2", "Doe", "Smith",
                "doesmith@gmail.com"));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return UserController.users;
    }

    @GetMapping("/users/{username}")
    public User getUserById(@PathVariable String username) {
        Optional<User> searchedUser = users.stream().filter( user -> username.equals(user.getUsername())).findFirst();
        return searchedUser.orElse(null);
    }

    @PostMapping("/")
    public String createUser(@RequestBody User user) {
        users.add(user);
        return "User Added";
    }

    @DeleteMapping("/users/{username}")
    public String deleteUser(@PathVariable String username) {
        Optional<User> deleteUser = users.stream().filter(user -> username.equals(user.getUsername())).findFirst();

        deleteUser.ifPresent(user -> UserController.users.remove(user));

        return deleteUser.isPresent() ? "User Deleted!!!" : "User Doesn't Exist!!!";
    }

    @PutMapping("/users/{username}")
    public String updateUser(@RequestBody User updateUser) {
        Optional<User> targetUser = users.stream().filter(user -> updateUser.getUsername().equals(user.getUsername())).findFirst();

        if(targetUser.isPresent()){
            UserController.users.remove(targetUser.get());
            UserController.users.add(updateUser);
        }

        return targetUser.isPresent() ? "User Updated!!!" : "User Doesn't Exist!!!";
    }

}
