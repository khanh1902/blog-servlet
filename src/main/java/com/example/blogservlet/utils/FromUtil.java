package com.example.blogservlet.utils;

import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@AllArgsConstructor
public class FromUtil {
    // convert from parameter to model
    @SuppressWarnings("unchecked")
    public static <T> T toModel(Class<T> fromClass, HttpServletRequest request) { // static de khoi phai khoi tao class
        T object = null;
        try {
            object = fromClass.newInstance();
            BeanUtils.populate(object, request.getParameterMap());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.print(e.getMessage());
        }
        return object;
    }
}
