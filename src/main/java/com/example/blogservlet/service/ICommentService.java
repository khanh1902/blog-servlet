package com.example.blogservlet.service;

import com.example.blogservlet.model.Comment;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;

import java.util.List;

public interface ICommentService {
    Page<Comment> findAll(Comment comment, Pageable pageable);
    List<Comment> findAllByNewId(Long newid);
    Comment save(Comment comment);
    void deleteById(List<Long> ids);
    int getTotalItem();
}
