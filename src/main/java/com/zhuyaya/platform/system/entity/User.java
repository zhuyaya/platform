package com.zhuyaya.platform.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import com.zhuyaya.platform.common.annotation.IsMobile;
import com.zhuyaya.platform.common.converter.TimeConverter;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户信息表
 * @author: zhuyaya
 * @date: 2021/12/20 8:42 下午
 */
@Data
@TableName("t_user")
@Excel("用户信息表")
public class User implements Serializable {

    private static final long serialVersionUID = -7661677980810157892L;

    /** 用户状态: 有效 */
    public static final String STATUS_VALID = "1";
    /** 用户状态: 锁定 */
    public static final String STATUS_LOCK = "0";
    /** 默认头像 */
    public static final String DEFAULT_AVATAR = "default.jpg";
    /** 默认密码 */
    public static final String DEFAULT_PASSWORD = "1234qwer";
    /** 性别男 */
    public static final String SEX_MALE = "0";
    /** 性别女 */
    public static final String SEX_FEMALE = "1";
    /** 性别保密 */
    public static final String SEX_UNKNOWN = "2";
    /** 黑色主题 */
    public static final String THEME_BLACK = "black";
    /** 白色主题 */
    public static final String THEME_WHITE = "white";
    /** TAB 开启 */
    public static final String TAB_OPEN = "1";
    /** TAB 关闭 */
    public static final String TAB_CLOSE = "0";

    /**
     * 用户ID
     */
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField("USERNAME")
    @Size(min = 4, max = 10, message = "{range}")
    @ExcelField(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 部门 ID
     */
    @TableField("DEPT_ID")
    private Long deptId;

    /**
     * 邮箱
     */
    @TableField("EMAIL")
    @Size(max = 50, message = "{noMoreThan}")
    @Email(message = "{email}")
    @ExcelField(value = "邮箱")
    private String email;

    /**
     * 联系电话
     */
    @TableField("MOBILE")
    @IsMobile(message = "{mobile}")
    @ExcelField(value = "联系电话")
    private String mobile;

    /**
     * 状态 0:锁定 1:有效
     */
    @TableField("STATUS")
    @NotBlank(message = "{required}")
    @ExcelField(value = "状态", writeConverterExp = "0=锁定,1=有效")
    private String status;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("MODIFY_TIME")
    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date modifyTime;

    /**
     * 最近访问时间
     */
    @TableField("LAST_LOGIN_TIME")
    @ExcelField(value = "最近访问时间", writeConverter = TimeConverter.class)
    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分ss秒", timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 性别 0:男 1:女 2:保密
     */
    @TableField("SSEX")
    @NotBlank(message = "{required}")
    @ExcelField(value = "性别", writeConverterExp = "0=男,1=女,2=保密")
    private String sex;

    /**
     * 头像
     */
    @TableField("AVATAR")
    private String avatar;

    /**
     * 主题
     */
    @TableField("THEME")
    private String theme;

    /**
     * 是否开启 tab 0:开启 1:关闭
     */
    @TableField("IS_TAB")
    private String isTab;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    @Size(max = 100, message = "{noMoreThan}")
    @ExcelField(value = "个人描述")
    private String description;

    /**
     * 部门名称
     */
    @ExcelField(value = "部门")
    @TableField(exist = false)
    private String deptName;

    /**
     * 创建时间-起
     */
    @TableField(exist = false)
    private String createTimeFrom;

    /**
     * 创建时间-去
     */
    @TableField(exist = false)
    private String createTimeTo;

    /**
     * 角色ID
     */
    @NotBlank(message = "{required}")
    @TableField(exist = false)
    private String roleId;

    /**
     * 角色名
     */
    @ExcelField(value = "角色")
    @TableField(exist = false)
    private String roleName;

    public Long getId() {
        return userId;
    }
}
