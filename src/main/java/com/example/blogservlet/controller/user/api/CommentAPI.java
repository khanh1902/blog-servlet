package com.example.blogservlet.controller.user.api;

import com.example.blogservlet.model.Comment;
import com.example.blogservlet.model.User;
import com.example.blogservlet.service.ICommentService;
import com.example.blogservlet.utils.HttpUtil;
import com.example.blogservlet.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-comment"})
public class CommentAPI extends HttpServlet {
    @Inject
    private ICommentService commentService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Comment comment = HttpUtil.of(req.getReader()).toModel(Comment.class);
        User user = (User) SessionUtil.getInstance().getValue(req, "USERMODEL");
        comment.setUserId(user.getId());
        comment = commentService.save(comment);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), comment);
    }
}
