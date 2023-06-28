package com.example.blogservlet.model;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Abstract<User> {
    private Long id;
    private String username;
    private String password;
    private String fullname;
    private Integer status;
    private Long roleId;
    private Timestamp createdDate;
    private Role role = new Role();
}
