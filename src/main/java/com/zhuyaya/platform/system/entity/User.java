package com.zhuyaya.platform.system.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description: 用户信息表
 * @author: zhuyaya
 * @date: 2021/12/20 8:42 下午
 */
@Data
public class User {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 部门 ID
     */
    private Long deptId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 状态 0:锁定 1:有效
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;
}
