package com.showLive.mds.common.dto;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by junyu-pc on 2016/4/16.
 */
public abstract class BaseController {
    protected String getToken(HttpServletRequest request){
        return request.getParameter("token");
    }
}
