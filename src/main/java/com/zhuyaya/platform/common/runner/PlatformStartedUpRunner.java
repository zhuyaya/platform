package com.zhuyaya.platform.common.runner;

import com.zhuyaya.platform.common.properties.PlatformProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;

/**
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 17:45
 */
@Component
@Slf4j
public class PlatformStartedUpRunner implements ApplicationRunner {

    @Resource
    private ConfigurableApplicationContext context;
    @Resource
    private PlatformProperties platformProperties;

    @Value("${server.port}")
    private String port;
    @Value("${server.servlet.context-path:}")
    private String contextPath;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (context.isActive()) {
            InetAddress address = InetAddress.getLocalHost();
            String url = String.format("http://%s:%s", address.getHostAddress(), port);
            String loginUrl = platformProperties.getShiro().getLoginUrl();
            if (StringUtils.isNotBlank(contextPath)) {
                url += contextPath;
            }
            if (StringUtils.isNotBlank(loginUrl)){
                url += loginUrl;
            }
            log.info(" __    ___   _      ___   _     ____ _____  ____ ");
            log.info("/ /`  / / \\ | |\\/| | |_) | |   | |_   | |  | |_  ");
            log.info("\\_\\_, \\_\\_/ |_|  | |_|   |_|__ |_|__  |_|  |_|__ ");
            log.info("                                                      ");
            log.info("Platform 系统启动完毕，地址：{}", url);
        }
    }
}