package com.zhuyaya.platform.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhuyaya.platform.system.entity.Role;

import java.util.List;

/**
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 15:32
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findUserRole(String username);
}