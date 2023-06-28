package com.example.blogservlet.dao.impl;

import com.example.blogservlet.dao.INewDAO;
import com.example.blogservlet.mapper.impl.NewMapperImpl;
import com.example.blogservlet.model.News;
import com.example.blogservlet.paging.Pageable;

import java.util.List;

public class NewDAOImpl extends GenericDAOImpl<News> implements INewDAO {
    @Override
    public List<News> findByCategoryIdWithPaging(Long categoryId, Pageable pageable) {
        StringBuilder sql = new StringBuilder( "SELECT * FROM (SELECT * FROM news ");
        if (pageable.getSorter().getSortName() != null) {
            if (pageable.getSorter().getAsc())
                sql.append("ORDER BY ").append(pageable.getSorter().getSortName()).append(" asc ");
            else
                sql.append("ORDER BY ").append(pageable.getSorter().getSortName()).append(" desc ");
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null)
            sql.append("LIMIT ").append(pageable.getOffset()).append(", ").append(pageable.getLimit());
        sql.append(") AS rs WHERE rs.categoryid = ? ");
        return query(sql.toString(), new NewMapperImpl(), categoryId);
    }

    @Override
    public List<News> findByCategoryId(Long categoryId) {
        String sql = "SELECT * FROM news WHERE categoryid = ?";
        return query(sql, new NewMapperImpl(), categoryId);
    }

    @Override
    public List<News> findAll() {
        String sql = "SELECT * FROM news";
        return query(sql, new NewMapperImpl());
    }

    @Override
    public List<News> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM news ");
        if (pageable.getSorter().getSortName() != null) {
            if (pageable.getSorter().getAsc())
                sql.append("ORDER BY ").append(pageable.getSorter().getSortName()).append(" asc ");
            else
                sql.append("ORDER BY ").append(pageable.getSorter().getSortName()).append(" desc ");
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null)
            sql.append("LIMIT ").append(pageable.getOffset()).append(", ").append(pageable.getLimit());

        return query(sql.toString(), new NewMapperImpl());
    }


    @Override
    public News findById(Long id) {
        String sql = "SELECT * FROM news WHERE id = ? ";
        List<News> news = query(sql, new NewMapperImpl(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public Long save(News news) {
        StringBuilder sql = new StringBuilder("INSERT INTO news(title, thumbnail, shortdescription,");
        sql.append(" content, categoryid, createdby, createddate) VALUES(?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), news.getTitle(), news.getThumbnail(), news.getShortDescription(),
                news.getContent(), news.getCategoryId(), news.getCreatedBy(), news.getCreatedDate());
    }

    @Override
    public void update(News news) {
        StringBuilder sql = new StringBuilder("UPDATE news ")
                .append("SET title = ?, thumbnail = ?, shortdescription = ?, content = ?, categoryid = ? , modifieddate = ?, modifiedby = ?")
                .append(" WHERE id = ?");
        update(sql.toString(), news.getTitle(), news.getThumbnail(), news.getShortDescription(),
                news.getContent(), news.getCategoryId(), news.getModifiedDate(), news.getModifiedBy(), news.getId());
    }

    @Override
    public void deleteById(Long newId) {
        String sql = "DELETE FROM news WHERE id = ?";
        deleteById(sql, newId);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM news";
        return count(sql);
    }
}
