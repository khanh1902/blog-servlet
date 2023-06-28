package com.example.blogservlet.service.impl;

import com.example.blogservlet.dao.IUserDAO;
import com.example.blogservlet.dto.RegisterDTO;
import com.example.blogservlet.model.User;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;
import com.example.blogservlet.service.IUserService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class UserServiceImpl implements IUserService {
    @Inject
    private IUserDAO userDAO;

    @Override
    public User findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
        return userDAO.findByUsernameAndPasswordAndStatus(username, password, status);
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public Page<User> findAllWithPaging(User user, Pageable pageable) {
        user.setListResult(userDAO.findAllWithPaging(pageable));
        user.setTotalItem(userDAO.getTotalItem());
        double math = (double) user.getTotalItem() / user.getLimit();
        user.setTotalPages((int) Math.ceil(math));
        return new Page<>(user.getListResult(), user.getOffset(), user.getLimit(), user.getTotalItem(), user.getTotalPages(),
                user.getSortName(), user.getAsc(), null);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User save(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setFullname(registerDTO.getFullname());
        if(registerDTO.getRoleId() == null) // dung cho nguoi dung dang ki tk
            user.setRoleId(2L);
        else user.setRoleId(registerDTO.getRoleId());
        user.setStatus(1);

        if(registerDTO.getPassword().equals(registerDTO.getConfirmPassword()))
            user.setPassword(registerDTO.getPassword());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreatedDate(timestamp);
        Long id = userDAO.save(user);
        return userDAO.findById(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        for(Long id : ids){
            userDAO.deleteById(id);
        }
    }

    @Override
    public int getTotalItem() {
        return userDAO.getTotalItem();
    }
}
