package com.example.blogservlet.dao;

import com.example.blogservlet.model.News;
import com.example.blogservlet.model.User;
import com.example.blogservlet.paging.Pageable;

import java.util.List;

public interface IUserDAO extends IGenericDAO<User> {
    User findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
    Long save(User user);
    User findById(Long id);
    List<User> findAllWithPaging(Pageable pageable);
    List<User> findAll();
    void deleteById(Long id);
    int getTotalItem();

}
