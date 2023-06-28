package com.example.blogservlet.service;

import com.example.blogservlet.dto.NewsDTO;
import com.example.blogservlet.model.News;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;

import java.util.List;

public interface INewsService {
    Page<News> findByCategoryIdWithPaging(News news, Long categoryId, Pageable pageable);
    List<News> findByCategoryId(Long categoryId);
    List<News> findAll();
//    News save(News news);
    News save(NewsDTO newsDTO);
    News update(NewsDTO newsDTO);
    void deleteById(List<Long> ids);
    Page<News> findAll(News news, Pageable pageable);
    int getTotalItem();
    News findById(Long id);

}
