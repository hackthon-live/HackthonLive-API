package com.showLive.mds.common.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanyan.wang
 * @date 2015-12-16 16:16
 */
public class ParamsMap {

    private Map<String, Object> params;

    private ParamsMap() {
        params = new HashMap<String, Object>();
    }

    public ParamsMap put(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public static ParamsMap create(String key, Object value) {
        ParamsMap paramsMap = new ParamsMap();
        paramsMap.put(key, value);
        return paramsMap;
    }

    public static ParamsMap empty() {
        ParamsMap paramsMap = new ParamsMap();
        return paramsMap;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}