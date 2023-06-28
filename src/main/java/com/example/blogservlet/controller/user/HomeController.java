package com.example.blogservlet.controller.user;

import com.example.blogservlet.model.Category;
import com.example.blogservlet.model.News;
import com.example.blogservlet.model.User;
import com.example.blogservlet.service.ICategoryService;
import com.example.blogservlet.service.INewsService;
import com.example.blogservlet.service.IRoleService;
import com.example.blogservlet.service.IUserService;
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
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/home", "/login", "/logout", "/register"})
public class HomeController extends HttpServlet {
    @Inject
    private IUserService userService;

    @Inject
    private INewsService newsService;

    @Inject
    private ICategoryService categoryService;

    @Inject
    private IRoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResourceBundle messageBundle = ResourceBundle.getBundle("application");
        String action = req.getParameter("action");
        String message = req.getParameter("message");
        String alert = req.getParameter("alert");
        if (action != null && action.equals("login")) {
            if (message != null && alert != null) {
                req.setAttribute("message", messageBundle.getString(message));
                req.setAttribute("alert", alert);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
            requestDispatcher.forward(req, resp);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeValue(req, "USERMODEL");
            resp.sendRedirect(req.getContextPath() + "/home");
        }
        else if (action != null && action.equals("register")) {
            MessageUtil.showMessage(req);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/register.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            News news = FromUtil.toModel(News.class, req); // get param
            String view = null;

            news.setListResult(newsService.findAll());
            req.setAttribute("newsModel", news);
            view = "/views/user/home.jsp";
            Category categories = new Category();
            categories.setListResult(categoryService.findAll());
            req.setAttribute("categoryModel", categories);
            req.setAttribute("roleAdminModel", roleService.findByCode("ADMIN")); // dung de truy cap den trang admin cho tk admin
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(view);
            requestDispatcher.forward(req, resp);
        }
    }

    // login
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            User user = FromUtil.toModel(User.class, req);
            user = userService.findByUsernameAndPasswordAndStatus(user.getUsername(), user.getPassword(), 1);
            if (user != null) {
                SessionUtil.getInstance().putValue(req, "USERMODEL", user);
                resp.sendRedirect(req.getContextPath() + "/home");
            } else
                resp.sendRedirect(req.getContextPath() + "/login?action=login&message=name_password_invalid&alert=danger");
        }
    }
}
