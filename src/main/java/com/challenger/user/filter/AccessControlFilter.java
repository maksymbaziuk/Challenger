package com.challenger.user.filter;

import com.challenger.user.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Maksym_Baziuk on 18.11.2015.
 */
public class AccessControlFilter extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null){
            httpServletResponse.setStatus(401);
            return false;
        }
        return true;
    }

}
