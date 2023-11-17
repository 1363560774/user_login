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
}
