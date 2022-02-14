package com.zhuyaya.platform.common.authentication;

import com.zhuyaya.platform.common.annotation.Helper;
import org.apache.shiro.authz.AuthorizationInfo;

/**
 * Shiro认证帮助类
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 8:49
 */
@Helper
public class ShiroHelper extends ShiroRealm{

    /**
     * 获取当前用户的角色和权限集合
     * @return 认证信息
     */
    public AuthorizationInfo getCurrentUserAuthorizationInfo(){
        return super.doGetAuthorizationInfo(null);
    }
}