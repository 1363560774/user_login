package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 侧边栏表
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@TableName("tb_sidebar")
@EqualsAndHashCode(callSuper = true)
public class Sidebar extends CommonFields {

    /**
     * 侧边栏id
     */
    @TableId
    private String sidebarId;
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
