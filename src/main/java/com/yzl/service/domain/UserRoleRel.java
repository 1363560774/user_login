package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户角色关联表
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@TableName("tb_user_role_rel")
@EqualsAndHashCode(callSuper = true)
public class UserRoleRel extends CommonFields {

    /**
     * 用户id
     */
    @TableId
    private String userId;
    /**
     * 角色id
     */
    private Byte roleId;
    @TableField(exist = false)
    private String roleName;
}
