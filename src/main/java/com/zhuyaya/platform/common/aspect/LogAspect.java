package com.zhuyaya.platform.common.aspect;

import com.zhuyaya.platform.common.properties.PlatformProperties;
import com.zhuyaya.platform.common.utils.HttpContextUtil;
import com.zhuyaya.platform.common.utils.IPUtil;
import com.zhuyaya.platform.monitor.entity.Log;
import com.zhuyaya.platform.monitor.service.ILogService;
import com.zhuyaya.platform.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * AOP 记录用户操作日志
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 8:44
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Resource
    private PlatformProperties platformProperties;

    @Resource
    private ILogService logService;

    @Pointcut("@annotation(com.zhuyaya.platform.common.annotation.Log)")
    public void pointcut() {
        // TODO: 切点处的逻辑处理
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 设置IP地址
        String ip = IPUtil.getIpArr(request);
        // 执行时长（毫秒）
        long time = System.currentTimeMillis() - beginTime;
        if (platformProperties.isOpenAopLog()) {
            // 保存日志
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            Log log = new Log();
            if (user != null) {
                log.setUsername(user.getUsername());
            }
            log.setIp(ip);
            log.setTime(time);
            logService.saveLog(point, log);
        }
        return result;
    }
}