package com.zhuyaya.platform.common.exception;

/**
 * 文件下载异常
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 16:48
 */
public class FileDownloadException extends Exception{

    private static final long serialVersionUID = 2564092421485687504L;

    public FileDownloadException(String message) {
        super(message);
    }
}