package com.example.blogservlet.dao;

import com.example.blogservlet.model.News;
import com.example.blogservlet.paging.Pageable;

import java.util.List;

public interface INewDAO extends IGenericDAO<News> {
    List<News> findByCategoryIdWithPaging(Long categoryId, Pageable pageable);
    List<News> findByCategoryId(Long categoryId);
    List<News> findAll();
    List<News> findAll(Pageable pageable);
    News findById(Long id);
    Long save(News news);
    void update(News news);
    void deleteById(Long newId);
    int getTotalItem();
}
