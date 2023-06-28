package com.example.blogservlet.service.impl;

import com.example.blogservlet.dao.ICategoryDAO;
import com.example.blogservlet.dao.INewDAO;
import com.example.blogservlet.model.Category;
import com.example.blogservlet.model.News;
import com.example.blogservlet.service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoriesServiceImpl implements ICategoryService {
    @Inject
    private ICategoryDAO categoryDAO;

    @Inject
    private INewDAO newDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDAO.findById(id);
    }

    @Override
    public Category save(Category category) {
        Long id = categoryDAO.save(category);
        return categoryDAO.findById(id);
    }

    @Override
    public Category update(Category category) {
        categoryDAO.update(category);
        return categoryDAO.findById(category.getId());
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        for (Long id : ids){
            List<News> newsList = newDAO.findByCategoryId(id);
            for(News news : newsList){
                newDAO.deleteById(news.getId());
            }
            categoryDAO.deleteById(id);
        }
    }

    @Override
    public int getTotalItem() {
        return categoryDAO.getTotalItem();
    }
}
