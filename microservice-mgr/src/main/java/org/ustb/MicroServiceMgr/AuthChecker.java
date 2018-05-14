package org.ustb.MicroServiceMgr;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthChecker implements HandlerInterceptor {
    //controller执行之前调用，检查是否登录
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //System.out.println("------preHandle-----");
        return true;
    }
}