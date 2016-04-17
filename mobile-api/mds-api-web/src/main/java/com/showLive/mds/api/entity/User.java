package com.showLive.mds.api.entity;

import lombok.Data;

/**
 * Created by junyu-pc on 2016/4/16.
 */
@Data
public class User {

    /**
     * id
     */
    private int id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * logo
     */
    private String logoUrl;

    /**
     * 粉丝数
     */
    private int fans;

    /**
     * 关注人数
     */

    private int follow;
    /**
     * icon
     */
    private String icon;
}
