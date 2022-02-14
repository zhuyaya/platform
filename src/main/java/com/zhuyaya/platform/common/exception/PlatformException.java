package com.zhuyaya.platform.common.exception;

/**
 * platform 系统内部异常
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 16:47
 */
public class PlatformException extends Exception{

    private static final long serialVersionUID = -76961026081160314L;

    public PlatformException(String message) {
        super(message);
    }
}