package com.zhuyaya.platform.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * http请求上下文的工具类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 15:55
 */
public class HttpContextUtil {
    private HttpContextUtil() {
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}