package com.example.blogservlet.controller.admin.api;

import com.example.blogservlet.dto.NewsDTO;
import com.example.blogservlet.model.News;
import com.example.blogservlet.model.User;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;
import com.example.blogservlet.paging.PageableImpl;
import com.example.blogservlet.service.INewsService;
import com.example.blogservlet.sort.Sorter;
import com.example.blogservlet.utils.FromUtil;
import com.example.blogservlet.utils.HttpUtil;
import com.example.blogservlet.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-new"})
@MultipartConfig(
        fileSizeThreshold = 1024*1024*2, // 2MB
        maxFileSize = 1024*1024*10, // 10MB
        maxRequestSize = 1024*1024*11   // 11MB
)
public class NewAPI extends HttpServlet {
    @Inject
    private INewsService newsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json"); // dinh dang kieu du lieu tra ve server
        ObjectMapper mapper = new ObjectMapper();
        News news = FromUtil.toModel(News.class, req);
        Pageable pageable = new PageableImpl(news.getOffset(), news.getLimit(),
                new Sorter(news.getSortName(), news.getAsc()));
        Page<News> paging = newsService.findAll(news, pageable);
        mapper.writeValue(resp.getOutputStream(), paging);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // dinh dang tieng viet
        resp.setContentType("application/json"); // dinh dang kieu du lieu tra ve server
        NewsDTO newsDTO = FromUtil.toModel(NewsDTO.class, req);
        newsDTO.setThumbnail(req.getPart("thumbnail"));
        User user = (User) SessionUtil.getInstance().getValue(req, "USERMODEL");
        newsDTO.setCreatedBy(user.getFullname());
        News news = newsService.save(newsDTO);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), news);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        NewsDTO newsDTO = FromUtil.toModel(NewsDTO.class, req);
        newsDTO.setThumbnail(req.getPart("thumbnail"));
        User user = (User) SessionUtil.getInstance().getValue(req, "USERMODEL");
        newsDTO.setModifiedBy(user.getFullname());
        News news = newsService.update(newsDTO);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), news);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        News news = HttpUtil.of(req.getReader()).toModel(News.class);
        newsService.deleteById(news.getIds());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), "Delete successfully!");
    }
}
