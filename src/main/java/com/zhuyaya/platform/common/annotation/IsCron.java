package com.zhuyaya.platform.common.annotation;

import com.zhuyaya.platform.common.validator.CronValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 判断是否为Cron表达式
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-29 9:33
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CronValidator.class)
public @interface IsCron {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
