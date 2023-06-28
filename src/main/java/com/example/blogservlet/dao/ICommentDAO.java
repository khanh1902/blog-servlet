package com.example.blogservlet.dao;

import com.example.blogservlet.model.Comment;
import com.example.blogservlet.paging.Pageable;

import java.util.List;

public interface ICommentDAO {
    List<Comment> findAll(Pageable pageable);
    List<Comment> findAllByNewsId(Long newsId);
    Comment findById(Long id);
    Long save(Comment comment);
    void deleteById(Long id);
    int getTotalItem();
}