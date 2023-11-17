package com.yzl.service.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author kai
 * @date 2023/11/16 16:37
 */
@Data
@TableName("tb_item")
@EqualsAndHashCode(callSuper = true)
public class Item extends CommonFields {

    /** 试题id ***/
    @TableId
    private String itemId;
    /** 题库id **/
    private String baseId;
    /** 试题编号 **/
    private Long itemCode;
    /** 题干 **/
    private String itemName;
    /** 题型 **/
    private String itemType;
    /** 答案 **/
    private String answer;
    /** 排序 **/
    private Integer showOrder;
    /** 状态 **/
    private String state;
    @TableField(exist = false)
    private String baseName;
    @TableField(exist = false)
    private List<ItemOption> optionList;
    @TableField(exist = false)
    private List<ItemSub> subList;
}
