package com.example.blogservlet.service;

import com.example.blogservlet.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    Category update(Category category);
    void deleteByIds(List<Long> ids);
    int getTotalItem();

}
