package com.harshal.todo.user.repository;

import com.harshal.todo.user.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

}
