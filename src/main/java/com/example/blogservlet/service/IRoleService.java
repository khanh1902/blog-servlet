package com.example.blogservlet.service;

import com.example.blogservlet.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    Role findByCode(String code);
}
