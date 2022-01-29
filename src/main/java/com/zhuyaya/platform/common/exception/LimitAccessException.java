package com.zhuyaya.platform.common.exception;

/**
 * 限流异常
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-29 15:27
 */
public class LimitAccessException extends Exception{

    private static final long serialVersionUID = 8583785553211463937L;

    public LimitAccessException(String message) {
        super(message);
    }
}