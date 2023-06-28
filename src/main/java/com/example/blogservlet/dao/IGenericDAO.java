package com.example.blogservlet.dao;

import com.example.blogservlet.mapper.IRowMapper;

import java.util.List;
import java.util.Objects;

public interface IGenericDAO<T> {
    <T> List<T> query(String sql, IRowMapper<T> rowMapper, Object...parameters);
    Long insert(String sql, Object... parameters);
    void update(String sql, Object... parameters);
    void deleteById(String sql, Object... parameters);
    int count(String sql, Object... parameters);
}
