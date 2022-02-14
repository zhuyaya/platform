package com.zhuyaya.platform.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色-菜单/按钮 关联
 *
 * @author zhengfeizhu
 * @version 1.0
 * @date 2022-02-08 11:07
 */
@Data
@TableName("t_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -4888012025136555771L;

    /**
     * 角色ID
     */
    @TableField("ROLE_ID")
    private Long roleId;

    /**
     * 菜单/按钮 ID
     */
    @TableField("MENU_ID")
    private Long menuId;
}