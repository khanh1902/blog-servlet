package com.example.blogservlet.service.impl;

import com.example.blogservlet.dao.ICommentDAO;
import com.example.blogservlet.model.Comment;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;
import com.example.blogservlet.service.ICommentService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class CommentServiceImpl implements ICommentService {
    @Inject
    private ICommentDAO commentDAO;

    @Override
    public Page<Comment> findAll(Comment comment, Pageable pageable) {
        comment.setListResult(commentDAO.findAll(pageable));
        comment.setTotalItem(commentDAO.getTotalItem());
        double math = (double) comment.getTotalItem() / comment.getLimit();
        comment.setTotalPages((int) Math.ceil(math));
        return new Page<>(comment.getListResult(), comment.getOffset(), comment.getLimit(), comment.getTotalItem(), comment.getTotalPages(),
                comment.getSortName(), comment.getAsc(), null);
    }

    @Override
    public List<Comment> findAllByNewId(Long newid) {
        return commentDAO.findAllByNewsId(newid);
    }

    @Override
    public Comment save(Comment comment) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        comment.setCreatedDate(timestamp);
        Long id = commentDAO.save(comment);
        return commentDAO.findById(id);
    }

    @Override
    public void deleteById(List<Long> ids) {
        for(Long id : ids){
            commentDAO.deleteById(id);
        }
    }

    @Override
    public int getTotalItem() {
        return commentDAO.getTotalItem();
    }
}
