package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@TableName("tb_user")
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends CommonFields {

    /**
     * 用户id
     */
    @TableId
    private String userId;
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
     * 用户状态（0->创建，1->激活， 2->删除）
     */
    private Short userStatus;
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
