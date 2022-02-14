package com.zhuyaya.platform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuyaya.platform.system.entity.Role;

import java.util.List;

/**
 * 角色接口
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 9:35
 */
public interface IRoleService extends IService<Role> {

    /**
     * 通过用户名查找用户角色
     * @param username 用户名
     * @return 用户角色集合
     */
    List<Role> findUserRole(String username);
}