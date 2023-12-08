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
@TableName("tb_td_menu")
@EqualsAndHashCode(callSuper = true)
public class TDesignMenu extends CommonFields {

    @TableId
    private Integer id;
    private Integer pid;
    private String name;
    private String path;
    private String icon;
    private String cnTitle;
    private String usTitle;
    private String component;
    private String redirect;
    private Integer showOrder;
    @TableField(exist = false)
    private Meta meta;
    @TableField(exist = false)
    private List<TDesignMenu> children;

    @Data
    public static class Meta {
        private Language title;
        private String icon;
    }

    @Data
    public static class Language {
        private String zh_CN;
        private String en_US;
    }
}
