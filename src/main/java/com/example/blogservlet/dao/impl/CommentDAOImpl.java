package com.example.blogservlet.dao.impl;

import com.example.blogservlet.dao.ICommentDAO;
import com.example.blogservlet.mapper.impl.CommentMapperImpl;
import com.example.blogservlet.mapper.impl.NewMapperImpl;
import com.example.blogservlet.model.Comment;
import com.example.blogservlet.paging.Pageable;

import java.util.List;

public class CommentDAOImpl extends GenericDAOImpl<Comment> implements ICommentDAO {
    @Override
    public List<Comment> findAll(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM comment ");
        if (pageable.getSorter().getSortName() != null) {
            if (pageable.getSorter().getAsc())
                sql.append("ORDER BY ").append(pageable.getSorter().getSortName()).append(" asc ");
            else
                sql.append("ORDER BY ").append(pageable.getSorter().getSortName()).append(" desc ");
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null)
            sql.append("LIMIT ").append(pageable.getOffset()).append(", ").append(pageable.getLimit());

        return query(sql.toString(), new CommentMapperImpl());
    }

    @Override
    public List<Comment> findAllByNewsId(Long newsId) {
        String sql = "SELECT * FROM comment WHERE newid = ?";
        return query(sql, new CommentMapperImpl(), newsId);
    }

    @Override
    public Comment findById(Long id) {
        String sql = "SELECT * FROM comment WHERE id = ?";
        List<Comment> comments = query(sql, new CommentMapperImpl(), id);
        return comments.isEmpty() ? null : comments.get(0);
    }

    @Override
    public Long save(Comment comment) {
        StringBuilder sql = new StringBuilder("INSERT INTO comment(content, userid, newid, createddate) ");
        sql.append("VALUES(?, ?, ?, ?)");
        return insert(sql.toString(), comment.getContent(), comment.getUserId(), comment.getNewId(), comment.getCreatedDate());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM comment WHERE id = ?";
        deleteById(sql, id);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM comment";
        return count(sql);
    }
}
