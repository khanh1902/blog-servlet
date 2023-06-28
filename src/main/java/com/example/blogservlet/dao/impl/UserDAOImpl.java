package com.example.blogservlet.dao.impl;

import com.example.blogservlet.dao.IUserDAO;
import com.example.blogservlet.mapper.impl.UserMapperImpl;
import com.example.blogservlet.model.User;
import com.example.blogservlet.paging.Pageable;

import java.util.List;

public class UserDAOImpl extends GenericDAOImpl<User> implements IUserDAO {

    @Override
    public User findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
        StringBuilder sql = new StringBuilder( "SELECT * FROM user AS u ");
        sql.append(" INNER JOIN role AS r ON  r.id = u.roleid");
        sql.append(" WHERE username = ? AND password = ? AND status = ?");
        List<User> users = query(sql.toString(), new UserMapperImpl(), username, password, status);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public Long save(User user) {
        StringBuilder sql = new StringBuilder( "INSERT INTO user(username, password, fullname, status, roleid) ");
        sql.append("VALUES(?, ?, ?, ?, ?)");
        return insert(sql.toString(), user.getUsername(), user.getPassword(), user.getFullname(), user.getStatus(), user.getRoleId());
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        List<User> users = query(sql, new UserMapperImpl(), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<User> findAllWithPaging(Pageable pageable) {
        StringBuilder sql = new StringBuilder("SELECT * FROM user ");
        if (pageable.getSorter().getSortName() != null) {
            if (pageable.getSorter().getAsc())
                sql.append("ORDER BY ").append(pageable.getSorter().getSortName()).append(" asc ");
            else
                sql.append("ORDER BY ").append(pageable.getSorter().getSortName()).append(" desc ");
        }
        if (pageable.getOffset() != null && pageable.getLimit() != null)
            sql.append("LIMIT ").append(pageable.getOffset()).append(", ").append(pageable.getLimit());

        return query(sql.toString(), new UserMapperImpl());
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return query(sql, new UserMapperImpl());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM user WHERE id = ?";
        deleteById(sql, id);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM user";
        return count(sql);
    }
}
