package com.iydsj.sw.api.service;

import java.util.Map;

/**
 * Created by junyu-pc on 2016/4/16.
 */
public interface LiveService {
    Map create(String token, String name );

    Map getLivingMap();

    int exitChanel(String token);
}
