package com.harshal.todo.user.domain;

import lombok.Data;

@Data
public class User {

    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String emailId;

}
