package com.example.blogservlet.service.impl;

import com.example.blogservlet.dao.IRoleDAO;
import com.example.blogservlet.model.Role;
import com.example.blogservlet.service.IRoleService;

import javax.inject.Inject;
import java.util.List;

public class RoleServiceImpl implements IRoleService {
    @Inject
    private IRoleDAO roleDAO;

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role findByCode(String code) {
        return roleDAO.findByCode(code);
    }
}
