package com.example.blogservlet.dao.impl;

import com.example.blogservlet.dao.IRoleDAO;
import com.example.blogservlet.mapper.impl.RoleMapperImpl;
import com.example.blogservlet.model.Role;

import java.util.List;

public class RoleDAOImpl extends GenericDAOImpl<Role> implements IRoleDAO {
    @Override
    public List<Role> findAll() {
        String sql = "SELECT * FROM role";
        return query(sql, new RoleMapperImpl());
    }

    @Override
    public Role findByCode(String code) {
        String sql = "SELECT * FROM role WHERE code = ?";
        List<Role> roles = query(sql, new RoleMapperImpl(), code);
        return roles.isEmpty() ? null : roles.get(0);
    }
}
