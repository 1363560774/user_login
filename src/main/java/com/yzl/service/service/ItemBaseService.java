package com.yzl.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzl.service.domain.ItemBase;

/**
 * @author kai
 * @date 2023/11/16 17:07
 */
public interface ItemBaseService extends IService<ItemBase> {

    /**
     * 获取题库树
     * @param baseId 题库树id
     * @return 题库树
     */
    ItemBase loadItemBaseTree(String baseId);

    /**
     * 编辑题库 没有主键时新增,有主键是修改
     * @param itemBase 题库信息
     * @return 修改数据条数
     */
    Integer editItemBase(ItemBase itemBase);
}
