package com.zhuyaya.platform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuyaya.platform.system.entity.Menu;

import java.util.List;

/**
 * 菜单接口
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 9:36
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查找用户权限集
     * @param username 用户名（系统唯一）
     * @return 用户权限集合
     */
    List<Menu> findUserPermissions(String username);
}