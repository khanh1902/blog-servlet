package com.example.blogservlet.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
    private static SessionUtil sessionUtil = null;

    public static SessionUtil getInstance() {
        // Kiem tra xem doi tuong da ton tai hay chua
        // Neu chua thi khoi tao
        // Neu ton tai thi goi lai -> tiet kiem bo nho
        if (sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }
        return sessionUtil;
    }

    public void putValue(HttpServletRequest req, String key, Object value){
        req.getSession().setAttribute(key, value);
    }

    public Object getValue(HttpServletRequest req, String key){
        return req.getSession().getAttribute(key);
    }

    public void removeValue(HttpServletRequest req, String key){
        req.getSession().removeValue(key);
    }
}
