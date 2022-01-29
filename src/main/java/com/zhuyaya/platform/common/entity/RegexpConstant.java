package com.zhuyaya.platform.common.entity;

/**
 * 正则常量
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-29 14:51
 */
public class RegexpConstant {

    /** 简单的正则 复杂可替换
     * ^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$
     */
    public static final String MOBILE_REG = "[1]\\d{10}";
}