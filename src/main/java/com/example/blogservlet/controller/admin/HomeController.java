package com.example.blogservlet.controller.admin;

import com.example.blogservlet.service.ICategoryService;
import com.example.blogservlet.service.ICommentService;
import com.example.blogservlet.service.INewsService;
import com.example.blogservlet.service.IUserService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-home"})
public class HomeController extends HttpServlet {
    @Inject
    private ICategoryService categoriesService;

    @Inject
    private INewsService newsService;

    @Inject
    private IUserService userService;

    @Inject
    private ICommentService commentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", commentService.getTotalItem());
        req.setAttribute("news", newsService.getTotalItem());
        req.setAttribute("comments", commentService.getTotalItem());
        req.setAttribute("users", userService.getTotalItem());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/dashboard/home.jsp");
        requestDispatcher.forward(req, resp);
    }
}
