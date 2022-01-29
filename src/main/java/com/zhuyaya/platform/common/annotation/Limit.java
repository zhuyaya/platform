package com.zhuyaya.platform.common.annotation;

import com.zhuyaya.platform.common.entity.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限制注解
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022 -01-29 15:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    /**
     * 资源名称.
     *
     * @return the string
     */
    String name() default "";

    /**
     * 资源 key.
     *
     * @return the string
     */
    String key() default "";

    /**
     * key 前缀.
     *
     * @return the string
     */
    String prefix() default "";

    /**
     * 时间范围，单位秒.
     *
     * @return the string
     */
    int period();

    /**
     * 限制访问次数.
     *
     * @return the int
     */
    int count();

    /**
     * 限制类型.
     *
     * @return the limit type
     */
    LimitType limitType() default LimitType.CUSTOMER;
}
