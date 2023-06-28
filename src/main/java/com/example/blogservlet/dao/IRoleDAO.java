package com.example.blogservlet.dao;

import com.example.blogservlet.model.Role;

import java.util.List;

public interface IRoleDAO {
    List<Role> findAll();
    Role findByCode(String code);
}
