package com.zhuyaya.platform.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 结束节点注解
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-29 9:21
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Documented
@Component
public @interface PlatformEndPoint {
    @AliasFor(annotation = Component.class)
    String value() default "";
}