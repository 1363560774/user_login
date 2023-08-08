package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * 角色表
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@TableName("tb_role")
public class UserRole {

    /**
     * 角色id
     */
    @TableId
    private Byte roleId;
    /**
     * 角色类型
     */
    private String roleType;
    @TableField(exist = false)
    private List<Sidebar> sidebarList;
}
