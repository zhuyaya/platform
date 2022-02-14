package com.zhuyaya.platform.common.handler;

import com.zhuyaya.platform.common.entity.PlatformResponse;
import com.zhuyaya.platform.common.exception.FileDownloadException;
import com.zhuyaya.platform.common.exception.LimitAccessException;
import com.zhuyaya.platform.common.exception.PlatformException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.ExpiredSessionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

/**
 * 全局异常捕获
 * Order 注解表示最顶层执行
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 16:49
 */
@Slf4j
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public PlatformResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息: ", e);
        return new PlatformResponse().fail().message("系统内部异常");
    }

    @ExceptionHandler(value = PlatformException.class)
    public PlatformResponse handleParamsInvalidException(PlatformException e) {
        log.error("非法参数引起的系统异常: ", e);
        return new PlatformResponse().fail().message(e.getMessage());
    }

    /**
     * 统一处理请求参数校验（实体对象传参）
     * @param e BindException 参数校验不通过异常
     * @return PlatformResponse
     */
    @ExceptionHandler(value = BindException.class)
    public PlatformResponse validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new PlatformResponse().code(HttpStatus.BAD_REQUEST).message(message.toString());
    }

    /**
     * 统一处理请求参数校验（普通传参）
     * @param e ConstraintViolationException 违反约束异常
     * @return PlatformResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public PlatformResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ",");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new PlatformResponse().code(HttpStatus.BAD_REQUEST).message(message.toString());
    }

    @ExceptionHandler(value = LimitAccessException.class)
    public PlatformResponse handleLimitAccessException(LimitAccessException e) {
        log.error("访问限制异常: ",e);
        return new PlatformResponse().code(HttpStatus.TOO_MANY_REQUESTS).message(e.getMessage());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public PlatformResponse handleUnauthorizedException(UnauthorizedException e) {
        log.error("未通过认证错误:",e);
        return new PlatformResponse().code(HttpStatus.FORBIDDEN).message(e.getMessage());
    }

    @ExceptionHandler(value = ExpiredSessionException.class)
    public PlatformResponse handleExpiredSessionException(ExpiredSessionException e) {
        log.error("session过期异常: ",e);
        return new PlatformResponse().code(HttpStatus.UNAUTHORIZED).message(e.getMessage());
    }

    @ExceptionHandler(value = FileDownloadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleFileDownloadException(FileDownloadException e) {
        log.error("文件下载异常:",e);
    }

}