package com.example.blogservlet.mapper.impl;

import com.example.blogservlet.mapper.IRowMapper;
import com.example.blogservlet.model.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentMapperImpl implements IRowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet) {
        try {
            Comment comment = new Comment();

            comment.setId(resultSet.getLong("id"));
            comment.setContent(resultSet.getString("content"));
            comment.setUserId(resultSet.getLong("userid"));
            comment.setNewId(resultSet.getLong("newid"));
            if(resultSet.getTimestamp("createdDate") != null)
                comment.setCreatedDate(resultSet.getTimestamp("createddate"));
            if(resultSet.getTimestamp("modifieddate") != null)
                comment.setModifiedDate(resultSet.getTimestamp("createdDate"));
            if(resultSet.getString("modifiedby") != null)
                comment.setModifiedBy(resultSet.getString("modifiedby"));

            return comment;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
