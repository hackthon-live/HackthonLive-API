package com.iydsj.sw.common.utils;

import java.util.Date;

/**
 * Created by junyu-pc on 2016/4/16.
 */
public class TokenUtils {
    public static String getToken( String userName ){
        return  SecurityUtils.md5Encrypt(  userName + (new Date().getTime()) );
    }
}
