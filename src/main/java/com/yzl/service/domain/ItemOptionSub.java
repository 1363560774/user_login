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
@TableName("tb_item_option_sub")
@EqualsAndHashCode(callSuper = true)
public class ItemOptionSub extends CommonFields {

    /** 小题选项id **/
    @TableId
    private String subOptionId;
    /** 小题id **/
    private String subItemId;
    /** 试题id **/
    private String itemId;
    /** 选项内容 **/
    private String optionName;
    /** 排序 **/
    private Integer showOrder;
    /** 是否正确 **/
    private Boolean correct;
}
