package com.zhuyaya.platform.common.validator;

import com.zhuyaya.platform.common.annotation.IsCron;
import org.quartz.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Cron 表达式合规校验
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-29 9:53
 */
public class CronValidator implements ConstraintValidator<IsCron, String> {
    @Override
    public void initialize(IsCron isCron) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return CronExpression.isValidExpression(value);
        } catch (Exception e) {
            return false;
        }
    }
}