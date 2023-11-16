package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kai
 * @date 2023/11/16 16:37
 */
@Data
@TableName("tb_item_base")
@EqualsAndHashCode(callSuper = true)
public class ItemBase extends CommonFields {

    /** 题库id **/
    @TableId
    private String baseId;
    /** 题库名称 **/
    private String baseName;
    /** 父类id **/
    private String parentId;
}
