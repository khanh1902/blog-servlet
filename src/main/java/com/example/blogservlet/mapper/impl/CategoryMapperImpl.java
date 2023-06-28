package com.example.blogservlet.mapper.impl;

import com.example.blogservlet.mapper.IRowMapper;
import com.example.blogservlet.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapperImpl implements IRowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet) {
        try {
            Category category = new Category();
            category.setId(resultSet.getLong("id"));
            category.setName(resultSet.getString("name"));
            category.setCode(resultSet.getString("code"));
            return category;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
