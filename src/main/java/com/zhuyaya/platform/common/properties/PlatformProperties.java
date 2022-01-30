package com.zhuyaya.platform.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 配置文件读取类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 8:47
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:platform.properties"})
@ConfigurationProperties(prefix = "platform")
public class PlatformProperties {

    private ShiroProperties shiro = new ShiroProperties();
    private boolean openAopLog = true;
}