package com.yzl.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzl.service.common.Page;
import com.yzl.service.domain.Item;

/**
 * @author kai
 * @date 2023/11/16 17:07
 */
public interface ItemService extends IService<Item> {

    /**
     * 试题分页查询
     * @param itemPage 分页及查询信息
     * @return 试题分页
     */
    Page<Item> loadItemPage(Page<Item> itemPage, Item item);
}
