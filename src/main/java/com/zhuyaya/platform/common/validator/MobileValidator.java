package com.zhuyaya.platform.common.validator;

import com.zhuyaya.platform.common.annotation.IsMobile;
import com.zhuyaya.platform.common.entity.RegexpConstant;
import com.zhuyaya.platform.common.utils.PlatformUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机号合法性校验
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-29 10:49
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {
    @Override
    public void initialize(IsMobile isMobile) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return PlatformUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}