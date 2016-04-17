package com.iydsj.sw.api.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by junyu-pc on 2016/4/17.
 */
public class AccessKeyInterceptor extends HandlerInterceptorAdapter {

    private static Log log= LogFactory.getLog(AccessKeyInterceptor.class);


    private String accessKeyParameterName="accessKey";
    private List<String> defaultAccessAllowedFrom;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("access");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS");
        response.setHeader("Allow", "GET");
        return true;
    }

    public List<String> getDefaultAccessAllowedFrom() {
        return defaultAccessAllowedFrom;
    }

    public void setDefaultAccessAllowedFrom(List<String> defaultAccessAllowedFrom) {
        this.defaultAccessAllowedFrom = defaultAccessAllowedFrom;
    }

}