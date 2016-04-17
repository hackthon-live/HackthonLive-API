package com.iydsj.sw.common.utils;

import java.util.UUID;

/**
 * @author wangyan
 * @date 2014-11-02 17:03
 */
public final class KeyHolder {
    /**
     * 生成主键
     * @return uuid字符串
     */
    public static String getKey() {
        return UUID.randomUUID().toString();
    }
}