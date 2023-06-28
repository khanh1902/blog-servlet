package com.example.blogservlet.mapper.impl;

import com.example.blogservlet.mapper.IRowMapper;
import com.example.blogservlet.model.Role;
import com.example.blogservlet.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapperImpl implements IRowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet) {
        try {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setFullname(resultSet.getString("fullname"));
            user.setStatus(resultSet.getInt("status"));
            user.setRoleId(resultSet.getLong("roleid"));
            if (resultSet.getTimestamp("createdDate") != null)
                user.setCreatedDate(resultSet.getTimestamp("createddate"));
            try {
                Role role = new Role();
                role.setName(resultSet.getString("name"));
                role.setCode(resultSet.getString("code"));
                user.setRole(role);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return user;
        } catch (SQLException e) {
            return null;
        }
    }
}
