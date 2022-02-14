package com.zhuyaya.platform.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhuyaya.platform.system.entity.Menu;
import com.zhuyaya.platform.system.mapper.MenuMapper;
import com.zhuyaya.platform.system.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 15:35
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Override
    public List<Menu> findUserPermissions(String username) {
        return null;
    }
}