package com.zhuyaya.platform.common.properties;

import lombok.Data;

/**
 * shiro 框架 参数映射类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 9:07
 */
@Data
public class ShiroProperties {

    private long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String unauthorizedUrl;
}