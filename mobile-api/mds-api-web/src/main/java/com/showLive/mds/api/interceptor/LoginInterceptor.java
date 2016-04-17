package com.showLive.mds.api.interceptor;

/**
 * Created by junyu-pc on 2016/4/16.
 */

import com.showLive.mds.api.dao.UserDao;
import com.showLive.mds.api.entity.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserDao userDao;

    private String[] excludedUrls;

    public void setExcludedUrls(String[] excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI().replace(request.getContextPath(),"");
        if(excludedUrls != null && excludedUrls.length != 0){
            for(String ex : excludedUrls){
                if(requestUrl.contains(ex)){
                    return true;
                }
            }
        }
        String token = request.getParameter("token");
        if( token == null ){
            refuse(response);
            return false;
        } else{
            User user = userDao.getUserByToken(token);
            if(user == null){
                refuse(response);
                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
    private void refuse (HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-type","text/html;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write("{\"code\":\"500\",\"msg\":\"请输入token\"}");
        writer.close();
    }
}
