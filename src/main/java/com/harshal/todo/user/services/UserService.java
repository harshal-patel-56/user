package com.harshal.todo.user.services;

import com.harshal.todo.user.domain.User;
import com.harshal.todo.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers(int pageNo, int userCount) {

        Pageable pageable = PageRequest.of(pageNo - 1, userCount);
        Page<User> userPage = userRepo.findAll(pageable);

        return userPage.getContent().isEmpty() ? null : userPage.getContent();

    }

    public User getUserByUsername(String username) {
        Optional<User> searchedUser = userRepo.findByUsername(username);
        return searchedUser.orElse(null);
    }

    public String createUser(User user) {
        Optional<User> searchedUser = userRepo.findByUsername(user.getUsername());
        if(searchedUser.isPresent()) {
            return "Username already taken";
        }
        else {
            userRepo.save(user);
            return "User Added";
        }
    }

    public String deleteUser(String username) {
        Optional<User> deleteUser = userRepo.findByUsername(username);

        deleteUser.ifPresent(user -> this.userRepo.deleteById(user.getId()));

        return deleteUser.isPresent() ? "User Deleted!!!" : "User Doesn't Exist!!!";
    }

    public String updateUser(User updateUser) {
        Optional<User> targetUser = userRepo.findByUsername(updateUser.getUsername());

        if(targetUser.isPresent()){
            userRepo.delete(targetUser.get());
            userRepo.save(updateUser);
        }

        return targetUser.isPresent() ? "User Updated!!!" : "User Doesn't Exist!!!";
    }

}
