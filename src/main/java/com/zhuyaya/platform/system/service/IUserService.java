package com.zhuyaya.platform.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhuyaya.platform.common.entity.QueryRequest;
import com.zhuyaya.platform.system.entity.User;

/**
 * 用户处理接口
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 8:58
 */
public interface IUserService extends IService<User> {

    /**
     * 通过用户名查找用户
     * @param username 用户名（系统唯一）
     * @return 用户
     */
    User findByName(String username);

    /**
     * 查找用户详细信息
     * @param user 用户对象，用于传递查询条件
     * @param request mybatis-plus 分页查询条件
     * @return 分页后result
     */
    IPage<User> findUserDetail(User user, QueryRequest request);

    /**
     * 通过用户名查找用户详细信息
     * @param username 用户名（系统唯一）
     * @return 用户详细信息
     */
    User findUserDetail(String username);

    /**
     * 更新用户登录时间
     * @param username 用户名（系统唯一）
     */
    void updateLoginTime(String username);
}