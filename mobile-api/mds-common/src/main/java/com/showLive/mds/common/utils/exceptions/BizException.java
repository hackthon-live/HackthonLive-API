package com.showLive.mds.common.utils.exceptions;

/**
 * @author wangyan
 * @date 2014-11-02 16:58
 */
public class BizException extends RuntimeException {
    /**
     * 构造函数
     */
    public BizException() {
        super();
    }

    /**
     * 构造函数
     * @param message 信息
     */
    public BizException(String message) {
        super(message);
    }
    /**
     * 构造函数
     * @param message 信息
     * @param cause cause
     */
    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * 构造函数
     * @param cause cause
     */
    public BizException(Throwable cause) {
        super(cause);
    }
}
