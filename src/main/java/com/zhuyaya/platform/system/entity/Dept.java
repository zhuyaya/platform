package com.zhuyaya.platform.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 部门信息表
 * @author: zhuyaya
 * @date: 2021/12/20 10:06 下午
 */
public class Dept implements Serializable {

    private static final long serialVersionUID = 587308216652400448L;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 上级部门ID
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 排序
     */
    private Long orderNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;
}
