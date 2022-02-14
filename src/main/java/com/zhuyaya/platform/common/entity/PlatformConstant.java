package com.zhuyaya.platform.common.entity;

/**
 * 系统常量
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 16:20
 */
public class PlatformConstant {

    /**
     * 排序规则: 降序
     */
    public static final String ORDER_DESC = "desc";

    /**
     * 排序规则: 升序
     */
    public static final String ORDER_ASC = "asc";

    /**
     * 前端页面路径前缀
     */
    public static final String VIEW_PREFIX = "platform/views/";

    /**
     * 验证码 Session Key
     */
    public static final String CODE_PREFIX = "platform_captcha_";

    /**
     * 允许下载的文件类型，根据需求自己添加（小写）
     */
    public static final String[] VALID_FILE_TYPE = {"xlsx", "zip"};
}