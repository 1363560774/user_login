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
@TableName("tb_item_sub")
@EqualsAndHashCode(callSuper = true)
public class ItemSub extends CommonFields {

    /** 小题id **/
    @TableId
    private String subItemId;
    /** 试题id **/
    private String itemId;
    /** 题干 **/
    private String itemName;
    /** 题型 **/
    private String itemType;
    /** 答案 **/
    private String answer;
    /** 排序 **/
    private Integer showOrder;
}
