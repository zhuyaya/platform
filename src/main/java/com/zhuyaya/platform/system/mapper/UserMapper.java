package com.zhuyaya.platform.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhuyaya.platform.system.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface User mapper.
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022 -02-08 15:09
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByName(String username);

    /**
     * 查找用户详细信息（分页）
     *
     * @param page 分页对象
     * @param user 用户对象，用来传递查询条件
     * @return the page
     */
    IPage<User> findUserDetailPage(Page<User> page, @Param("user") User user);

    /**
     * 查找用户详细信息
     *
     * @param user 用户对象，用来传递查询条件
     * @return the list
     */
    List<User> findUserDetail(@Param("user") User user);
}