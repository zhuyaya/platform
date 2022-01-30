package com.zhuyaya.platform.common.authentication;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.zhuyaya.platform.common.properties.PlatformProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Shiro 配置类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 16:15
 */
@Configuration
public class ShiroConfig {

    @Resource
    private PlatformProperties platformProperties;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.database:0}")
    private int database;

    /**
     * shiro 中配置 Redis 缓存
     * @return RedisManager
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host + ":" + port);
        System.out.println(host);
        if (StringUtils.isNotBlank(password)) {
            redisManager.setPassword(password);
        }
        redisManager.setTimeout(timeout);
        redisManager.setDatabase(database);
        return redisManager;
    }

    private RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 获取过滤器
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        // 设置 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 登录的 url
        shiroFilterFactoryBean.setLoginUrl(platformProperties.getShiro().getLoginUrl());
        // 登录成功后跳转的 url
        shiroFilterFactoryBean.setSuccessUrl(platformProperties.getShiro().getSuccessUrl());
        // 未授权 url
        shiroFilterFactoryBean.setUnauthorizedUrl(platformProperties.getShiro().getUnauthorizedUrl());

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>(8);
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(platformProperties.getShiro().getAnonUrl(), ",");
        for (String url: anonUrls) {
            filterChainDefinitionMap.put(url, "anon");
        }
        // 配置退出过滤器，调用Shiro中成熟的方法
        filterChainDefinitionMap.put(platformProperties.getShiro().getLogoutUrl(), "logout");

        // 除上述外所有url都必须认证通过才可以访问，未通过认证自动访问 LoginUrl
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置 SecurityManager，并注入 shiroRealm
        securityManager.setRealm(shiroRealm);
        // 配置 shiro session 管理器
        securityManager.setSessionManager(sessionManager());
        // 配置 缓存管理类 cacheManager
        securityManager.setCacheManager(cacheManager());
        // 配置 rememberMeCookie
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * cookie 管理对象
     * @return CookieRememberMeManager
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // 为 rememberMe cookie 加密
        String encryptKey = "platform_shiro_key";
        byte[] encryptKeyBytes = encryptKey.getBytes(StandardCharsets.UTF_8);
        String rememberKey = Base64Utils.encodeToString(Arrays.copyOf(encryptKeyBytes, 32));
        cookieRememberMeManager.setCipherKey(Base64.decode(rememberKey));
        return cookieRememberMeManager;
    }

    /**
     * rememberMe cookie 效果是重开游览器后无需重新登录
     * @return SimpleCookie
     */
    private SimpleCookie rememberMeCookie() {
        // 设置 cookie 名称，对应 login.html 页面的 <input type="checkbox" name="rememberMe">
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // 设置 cookie 的过期时间，单位为秒，当前配置为一天
        cookie.setMaxAge(platformProperties.getShiro().getCookieTimeout());
        return cookie;
    }

    /**
     * session 管理对象
     * @return DefaultWebSessionManager
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>(1);
        listeners.add(new ShiroSessionListener());
        // 设置 session 超时时间
        sessionManager.setGlobalSessionTimeout(platformProperties.getShiro().getSessionTimeout() * 1000L);
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 用于开启 Thymeleaf 中的 shiro 标签的使用
     * @return ShiroDialect shiro 方言对象
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}