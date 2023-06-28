package com.example.blogservlet.controller.admin;

import com.example.blogservlet.model.Category;
import com.example.blogservlet.service.ICategoryService;
import com.example.blogservlet.utils.FromUtil;
import com.example.blogservlet.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-category"})
public class CategoryController extends HttpServlet {
    @Inject
    private ICategoryService categoryService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = FromUtil.toModel(Category.class, req);
        String view = null;
        if(category.getType().equals("list")){
            category.setListResult(categoryService.findAll());
            req.setAttribute("model", category);
            view = "/views/admin/category/list.jsp";
        } else if (category.getType().equals("edit")){
            if(category.getId() != null){
                category = categoryService.findById(category.getId());
                req.setAttribute("model", category);
            }
            view = "/views/admin/category/edit.jsp";
        }
        MessageUtil.showMessage(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
        requestDispatcher.forward(req, resp);
    }
}
