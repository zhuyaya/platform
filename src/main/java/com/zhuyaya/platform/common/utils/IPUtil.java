package com.zhuyaya.platform.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * IP 工具类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-29 15:29
 */
public class IPUtil {

    private static final String UNKNOWN = "unknown";

    protected IPUtil() {
    }

    /**
     * 获取 IP 地址
     * 使用 Nginx 等反向代理软件， 则不能通过 request.getRemoteArr() 获取 IP 地址
     * 如果使用了多级反向代理的话， X-Forwarded-For 的值不止一个， 而是一串IP
     * X-Forward-For 中第一个非 unknown 的有效 IP 字符串， 则为真实IP地址
     * @param request http 请求
     * @return ip 地址
     */
    public static String getIpArr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (checkIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (checkIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (checkIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static boolean checkIp(String ip) {
        return ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip);
    }
}