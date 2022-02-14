package com.zhuyaya.platform.common.authentication;

import com.zhuyaya.platform.system.entity.Menu;
import com.zhuyaya.platform.system.entity.Role;
import com.zhuyaya.platform.system.entity.User;
import com.zhuyaya.platform.system.service.IMenuService;
import com.zhuyaya.platform.system.service.IRoleService;
import com.zhuyaya.platform.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义实现 ShiroRealm，包含认证和授权两大模块
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-01-30 17:11
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private IMenuService menuService;

    /**
     * 授权模块，获取用户角色和权限
     * @param principalCollection 初始获取的信息
     * @return 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<Role> roleList = roleService.findUserRole(username);
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<Menu> permissionList = this.menuService.findUserPermissions(username);
        Set<String> permissionSet = permissionList.stream().map(Menu::getPerms).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(permissionSet);

        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     * @param token 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        // 通过用户名到数据库查询用户信息
        User user = this.userService.findByName(username);

        if (user == null) {
            throw new UnknownAccountException("用户不存在，请注册！");
        }
        if (!StringUtils.equals(password, user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (User.STATUS_LOCK.equals(user.getStatus())) {
            throw new LockedAccountException("账号已被锁定，请联系管理员！");
        }

        return new SimpleAuthenticationInfo(user, password, getName());
    }

    /**
     * 清除当前用户权限缓存
     * 使用方法：在需要清除用户权限的地方注入 ShiroRealm，然后调用其 clearCache 方法
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}