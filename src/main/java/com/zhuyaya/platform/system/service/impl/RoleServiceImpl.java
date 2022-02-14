package com.zhuyaya.platform.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuyaya.platform.system.entity.Role;
import com.zhuyaya.platform.system.mapper.RoleMapper;
import com.zhuyaya.platform.system.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 15:32
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Override
    public List<Role> findUserRole(String username) {
        return null;
    }
}