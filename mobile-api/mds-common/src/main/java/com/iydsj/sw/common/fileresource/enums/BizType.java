package com.iydsj.sw.common.fileresource.enums;


public enum BizType {

    /**
     * 场馆
     */
    Venue(10),

    /**
     * 用户
     */
    User(20),

    /**
     * 商家
     */
    Business(30),

    /**
     * 其他
     */
    OTHER(40);

    private int value;

    BizType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
