package com.showLive.mds.common.utils.exceptions;


/**
 *
 * @date 2015-07-05 22:33
 */
public class UnSupportException extends BizException {

    /**
     * 构造函数
     */
    public UnSupportException() {
        super();
    }

    /**
     * 构造函数
     * @param message 信息
     */
    public UnSupportException(String message) {
        super(message);
    }
    /**
     * 构造函数
     * @param message 信息
     * @param cause cause
     */
    public UnSupportException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * 构造函数
     * @param cause cause
     */
    public UnSupportException(Throwable cause) {
        super(cause);
    }
}
