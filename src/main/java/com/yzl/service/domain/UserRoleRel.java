package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户角色关联表
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@TableName("tb_user_role_rel")
public class UserRoleRel {

    /**
     * 用户id
     */
    @TableId
    private Long userId;
    /**
     * 角色id
     */
    private Byte roleId;
    @TableField(exist = false)
    private String roleName;
}
