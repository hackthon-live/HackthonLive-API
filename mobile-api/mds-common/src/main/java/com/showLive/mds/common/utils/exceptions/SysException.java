package com.showLive.mds.common.utils.exceptions;

/**
 * @author wangyan
 * @date 2014-11-02 16:58
 */
public class SysException extends RuntimeException{
    /**
     * 构造函数
     */
    public SysException() {
        super();
    }

    /**
     * 构造函数
     * @param message 信息
     */
    public SysException(String message) {
        super(message);
    }
    /**
     * 构造函数
     * @param message 信息
     * @param cause cause
     */
    public SysException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * 构造函数
     * @param cause cause
     */
    public SysException(Throwable cause) {
        super(cause);
    }
}

