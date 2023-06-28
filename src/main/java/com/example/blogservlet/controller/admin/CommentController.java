package com.example.blogservlet.controller.admin;

import com.example.blogservlet.model.Comment;
import com.example.blogservlet.model.News;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;
import com.example.blogservlet.paging.PageableImpl;
import com.example.blogservlet.service.ICommentService;
import com.example.blogservlet.service.IUserService;
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

@WebServlet(urlPatterns = {"/admin-comment"})
public class CommentController extends HttpServlet {
    @Inject
    private ICommentService commentService;

    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Comment comment = FromUtil.toModel(Comment.class, req); // get param
        Pageable pageable = new PageableImpl(comment.getOffset(), comment.getLimit(),
                new Sorter(comment.getSortName(), comment.getAsc()));
        Page<Comment> paging = commentService.findAll(comment, pageable);
        req.setAttribute("model", paging);
        req.setAttribute("userModel", userService.findAll());
        MessageUtil.showMessage(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/admin/comment/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
