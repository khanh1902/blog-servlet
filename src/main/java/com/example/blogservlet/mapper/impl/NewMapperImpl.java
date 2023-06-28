package com.example.blogservlet.mapper.impl;

import com.example.blogservlet.mapper.IRowMapper;
import com.example.blogservlet.model.News;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewMapperImpl implements IRowMapper<News> {
    @Override
    public News mapRow(ResultSet resultSet) {
        try {
            News news = new News();
            news.setId(resultSet.getLong("id"));
            news.setTitle(resultSet.getString("title"));
            news.setContent(resultSet.getString("content"));
            news.setThumbnail(resultSet.getString("thumbnail"));
            news.setShortDescription(resultSet.getString("shortdescription"));
            news.setCategoryId(resultSet.getLong("categoryid"));
            if(resultSet.getTimestamp("createdDate") != null)
                news.setCreatedDate(resultSet.getTimestamp("createddate"));
            news.setCreatedBy(resultSet.getString("createdby"));
            if(resultSet.getTimestamp("modifieddate") != null)
                news.setModifiedDate(resultSet.getTimestamp("createdDate"));
            if(resultSet.getString("modifiedby") != null)
                news.setModifiedBy(resultSet.getString("modifiedby"));
            return news;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
