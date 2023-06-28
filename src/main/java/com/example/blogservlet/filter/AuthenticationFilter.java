package com.example.blogservlet.filter;

import com.example.blogservlet.model.User;
import com.example.blogservlet.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String url = req.getServletPath();
        if (url.startsWith("/admin")) {
            User user = (User) SessionUtil.getInstance().getValue(req, "USERMODEL");
            if (user != null) {
                if (user.getRole().getCode().equals("ADMIN")) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (user.getRole().getCode().equals("USER"))
                    res.sendRedirect(req.getContextPath() + "/login?action=login&message=not_permission&alert=danger");
            } else {
                res.sendRedirect(req.getContextPath() + "/login?action=login&message=not_login&alert=danger");
            }
        } else
            filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
