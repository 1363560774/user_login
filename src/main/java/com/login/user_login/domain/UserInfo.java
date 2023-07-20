package com.login.user_login.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 用户信息
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@TableName("tb_user_info")
public class UserInfo {

    /**
     * 用户id
     */
    @TableId
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 图像路径
     */
    private String avatar;
    /**
     * 性别
     */
    private Boolean sex;
    /**
     * 年龄
     */
    private Byte age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private Long phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 用户状态（0->创建，1->激活）
     */
    private Boolean userStatus;
    /**
     * 注册时间
     */
    private Date createTime;
    /**
     * 个人简介
     */
    private String introduction;
    @TableField(exist = false)
    private String token;
    @TableField(exist = false)
    private String organizationName;
    @TableField(exist = false)
    private Long organizationId;
    @TableField(exist = false)
    private UserRole userRole;
    @TableField(exist = false)
    private String roleName;
    @TableField(exist = false)
    private Byte roleId;
}
