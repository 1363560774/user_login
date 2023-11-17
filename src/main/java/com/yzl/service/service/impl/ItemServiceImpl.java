package com.yzl.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzl.service.common.Page;
import com.yzl.service.domain.Item;
import com.yzl.service.mapper.ItemMapper;
import com.yzl.service.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kai
 * @date 2023/11/16 17:08
 */
@Slf4j
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Page<Item> loadItemPage(Page<Item> itemPage, Item item) {
        Page<Item> page = new Page<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(false);
        page.addOrder(orderItem);
        page.setCurrent(itemPage.getCurrent());
        page.setSize(itemPage.getSize());
        return itemMapper.selectPage(page, Wrappers.<Item>lambdaQuery()
                //题型
                .eq(StringUtils.isNotBlank(item.getItemType()), Item::getItemType, item.getItemType())
                //状态
                .eq(StringUtils.isNotBlank(item.getState()), Item::getState, item.getState())
                //题库
                .eq(StringUtils.isNotBlank(item.getBaseId()), Item::getBaseId, item.getBaseId())
                //题干或试题编号
                .and(StringUtils.isNotEmpty(itemPage.getFilter()),
                        wrapper -> wrapper.like(Item::getItemName, itemPage.getFilter()).or()
                                .eq(Item::getItemCode, itemPage.getFilter())));
    }

    @Override
    public Item addItem(Item item) {
        return null;
    }
}
