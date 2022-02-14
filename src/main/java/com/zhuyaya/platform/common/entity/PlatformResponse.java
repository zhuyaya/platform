package com.zhuyaya.platform.common.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * 系统统一web响应类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 16:31
 */
public class PlatformResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = 2940506091508037285L;

    public PlatformResponse code(HttpStatus status) {
        this.put("code", status.value());
        return this;
    }

    public PlatformResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public PlatformResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    public PlatformResponse success() {
        this.code(HttpStatus.OK);
        return this;
    }

    public PlatformResponse fail() {
        this.code(HttpStatus.INTERNAL_SERVER_ERROR);
        return this;
    }

    @Override
    public PlatformResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}