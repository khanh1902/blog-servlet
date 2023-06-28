package com.example.blogservlet.controller.admin;

import com.example.blogservlet.model.News;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;
import com.example.blogservlet.paging.PageableImpl;
import com.example.blogservlet.service.ICategoryService;
import com.example.blogservlet.service.INewsService;
import com.example.blogservlet.sort.Sorter;
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

@WebServlet(urlPatterns = {"/admin-new"})
public class NewsController extends HttpServlet {
    @Inject
    private INewsService newsService;

    @Inject
    private ICategoryService categoriesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        News news = FromUtil.toModel(News.class, req); // get param
        String views = null;
        if (news.getType().equals("list")) {
            Pageable pageable = new PageableImpl(news.getOffset(), news.getLimit(),
                    new Sorter(news.getSortName(), news.getAsc()));
            Page<News> paging = newsService.findAll(news, pageable);
            req.setAttribute("model", paging);
            views = "/views/admin/news/list.jsp";
        } else if (news.getType().equals("edit")) {
            if (news.getId() != null) {
                news = newsService.findById(news.getId());
                req.setAttribute("model", news);
            }
            req.setAttribute("categories", categoriesService.findAll());
            views = "/views/admin/news/edit.jsp";
        }
        MessageUtil.showMessage(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(views);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
