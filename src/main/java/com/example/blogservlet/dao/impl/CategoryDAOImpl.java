package com.example.blogservlet.dao.impl;

import com.example.blogservlet.dao.ICategoryDAO;
import com.example.blogservlet.mapper.impl.CategoryMapperImpl;
import com.example.blogservlet.model.Category;

import java.util.List;

public class CategoryDAOImpl extends GenericDAOImpl<Category> implements ICategoryDAO {
    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        return query(sql, new CategoryMapperImpl());
    }

    @Override
    public Category findById(Long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<Category> findCategory = query(sql, new CategoryMapperImpl(), id);
        return findCategory.isEmpty() ? null : findCategory.get(0);
    }

    @Override
    public Long save(Category category) {
        String sql = "INSERT INTO category(name, code)  VALUES(?, ?)";
        return insert(sql, category.getName(), category.getCode());
    }

    @Override
    public void update(Category category) {
        String sql = "UPDATE category SET name = ?, code = ? WHERE id = ?";
        update(sql, category.getName(), category.getCode(), category.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM category WHERE id = ?";
        deleteById(sql, id);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM category";
        return count(sql);
    }
}
