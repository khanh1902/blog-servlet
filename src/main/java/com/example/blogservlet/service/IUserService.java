package com.example.blogservlet.service;

import com.example.blogservlet.dto.RegisterDTO;
import com.example.blogservlet.model.News;
import com.example.blogservlet.model.User;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;

import java.util.List;

public interface IUserService {
    User findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
    User findById(Long id);
    List<User> findAll();
    Page<User> findAllWithPaging(User user, Pageable pageable);
    User save(RegisterDTO registerDTO);
    void deleteByIds(List<Long> ids);
    int getTotalItem();
}
