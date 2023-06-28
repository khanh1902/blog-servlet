package com.example.blogservlet.dao;

import com.example.blogservlet.model.Category;

import java.util.List;

public interface ICategoryDAO extends IGenericDAO<Category>{
    List<Category> findAll();
    Category findById(Long id);
    Long save(Category category);
    void update(Category category);
    void deleteById(Long id);
    int getTotalItem();
}
