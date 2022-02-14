package com.zhuyaya.platform.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuyaya.platform.common.entity.QueryRequest;
import com.zhuyaya.platform.system.entity.User;
import com.zhuyaya.platform.system.mapper.UserMapper;
import com.zhuyaya.platform.system.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 15:06
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User findByName(String username) {
        return null;
    }

    @Override
    public IPage<User> findUserDetail(User user, QueryRequest request) {
        return null;
    }

    @Override
    public User findUserDetail(String username) {
        return null;
    }

    @Override
    public void updateLoginTime(String username) {

    }
}