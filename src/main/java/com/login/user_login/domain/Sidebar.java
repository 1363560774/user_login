package com.login.user_login.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 侧边栏表
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@TableName("tb_sidebar")
public class Sidebar {

    /**
     * 侧边栏id
     */
    @TableId
    private Byte sidebarId;
    /**
     * 侧边栏路径
     */
    private String sidebarPath;
    /**
     * 侧边栏名称
     */
    private String sidebarName;
    /**
     * 侧边栏图标
     */
    private String sidebarIcon;
}
