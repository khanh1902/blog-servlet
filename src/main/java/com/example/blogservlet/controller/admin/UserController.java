package com.example.blogservlet.controller.admin;

import com.example.blogservlet.model.User;
import com.example.blogservlet.paging.Page;
import com.example.blogservlet.paging.Pageable;
import com.example.blogservlet.paging.PageableImpl;
import com.example.blogservlet.service.IRoleService;
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

@WebServlet(urlPatterns = {"/admin-user"})
public class UserController extends HttpServlet {

    @Inject
    private IUserService userService;

    @Inject
    private IRoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = FromUtil.toModel(User.class, req); // get param
        String views = null;
        if (user.getType().equals("list")) {
            Pageable pageable = new PageableImpl(user.getOffset(), user.getLimit(),
                    new Sorter(user.getSortName(), user.getAsc()));
            Page<User> paging = userService.findAllWithPaging(user, pageable);
            req.setAttribute("model", paging);
            views = "/views/admin/user/list.jsp";
        } else if (user.getType().equals("edit")) {
            views = "/views/admin/user/edit.jsp";
        }
        req.setAttribute("roleModel", roleService.findAll());
        MessageUtil.showMessage(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(views);
        requestDispatcher.forward(req, resp);
    }
}
