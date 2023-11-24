package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author kai
 * @date 2023/11/22 17:32
 */
@Data
@TableName("tb_menu")
@EqualsAndHashCode(callSuper = true)
public class Menu extends CommonFields {

    @TableId
    private Integer id;
    private String component;
    private String extend;
    private String icon;
    private Object keepalive;
    private String menuType;
    private String name;
    private String path;
    private Integer pid;
    private String title;
    private String type;
    private String url;
    private Integer showOrder;
    @TableField(exist = false)
    private List<Menu> children;
}
