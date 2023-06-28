package com.example.blogservlet.mapper.impl;

import com.example.blogservlet.mapper.IRowMapper;
import com.example.blogservlet.model.Category;
import com.example.blogservlet.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapperImpl implements IRowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet) {
        try {
            Role role = new Role();
            role.setId(resultSet.getLong("id"));
            role.setName(resultSet.getString("name"));
            role.setCode(resultSet.getString("code"));
            return role;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
