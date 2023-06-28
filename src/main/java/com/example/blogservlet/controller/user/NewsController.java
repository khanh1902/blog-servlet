package com.example.blogservlet.controller.user;

import com.example.blogservlet.model.News;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;
import com.example.blogservlet.paging.PageableImpl;
import com.example.blogservlet.service.ICategoryService;
import com.example.blogservlet.service.ICommentService;
import com.example.blogservlet.service.INewsService;
import com.example.blogservlet.service.IUserService;
import com.example.blogservlet.sort.Sorter;
import com.example.blogservlet.utils.FromUtil;
import com.example.blogservlet.utils.MessageUtil;
import com.example.blogservlet.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/new"})
public class NewsController extends HttpServlet {
    @Inject
    private IUserService userService;

    @Inject
    private INewsService newsService;

    @Inject
    private ICategoryService categoryService;

    @Inject
    private ICommentService commentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        News news = FromUtil.toModel(News.class, req); // get param
        String view = null;
        if (news.getCateId() != null) {
            Pageable pageable = new PageableImpl(news.getOffset(), news.getLimit(),
                    new Sorter(news.getSortName(), news.getAsc()));
            Page<News> paging = newsService.findByCategoryIdWithPaging(news, news.getCateId(), pageable);
            req.setAttribute("model", paging);
            req.setAttribute("categoryModel", categoryService.findAll());
            view = "/views/user/news/newsbycategory.jsp";
        } else if (news.getId() != null) {
            news = newsService.findById(news.getId());
            req.setAttribute("userModel", userService.findAll());
            req.setAttribute("commentModel", commentService.findAllByNewId(news.getId()));
            req.setAttribute("newModel", news);
            view = "/views/user/news/new.jsp";
        }
        MessageUtil.showMessage(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
        requestDispatcher.forward(req, resp);
    }
}
