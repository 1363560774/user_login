package com.yzl.service.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzl.service.common.ExceptionEnum;
import com.yzl.service.common.YzlException;
import com.yzl.service.domain.ItemBase;
import com.yzl.service.mapper.ItemBaseMapper;
import com.yzl.service.service.ItemBaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author kai
 * @date 2023/11/16 17:08
 */
@Slf4j
@Service
public class ItemBaseServiceImpl extends ServiceImpl<ItemBaseMapper, ItemBase> implements ItemBaseService {

    @Autowired
    private ItemBaseMapper itemBaseMapper;

    @Override
    public ItemBase loadItemBaseTree(String baseId) {
        if (StringUtils.isBlank(baseId)) {
            ItemBase itemBase = itemBaseMapper.selectOne(
                    Wrappers.<ItemBase>lambdaQuery().eq(ItemBase::getParentId, ""));
            baseId = itemBase.getBaseId();
        }
        List<ItemBase> itemBases = itemBaseMapper.selectItemBaseTree(baseId);
        ItemBase itemBase = itemBases.stream().findFirst().orElse(new ItemBase());
        if (StringUtils.isBlank(itemBase.getBaseName()) && StringUtils.isBlank(baseId)) {
            itemBase.setBaseId(IdWorker.getIdStr());
            itemBase.setParentId("");
            itemBase.setBaseName("全部");
            Date date = new Date();
            itemBase.setCreateTime(date);
            itemBase.setUpdateTime(date);
            itemBaseMapper.insert(itemBase);
            return itemBase;
        }
        Map<String, List<ItemBase>> baseMap = itemBases.stream()
                .collect(Collectors.groupingBy(ItemBase::getParentId));
        treeLoad(itemBase, baseMap);
        return itemBase;
    }

    @Override
    public Integer editItemBase(ItemBase itemBase) {
        if (StringUtils.isBlank(itemBase.getBaseName())) {
            throw new YzlException(ExceptionEnum.ITEM_BASE_NAME_IS_EMPTY);
        }
        if (StringUtils.isBlank(itemBase.getParentId())) {
            throw new YzlException(ExceptionEnum.ITEM_BASE_PARENT_ID_IS_EMPTY);
        }
        Date date = new Date();
        itemBase.setCreateTime(date);
        itemBase.setUpdateTime(date);
        if (StringUtils.isBlank(itemBase.getBaseId())) {
            itemBase.setBaseId(IdWorker.getIdStr());
            return itemBaseMapper.insert(itemBase);
        } else {
            return itemBaseMapper.updateById(itemBase);
        }
    }

    private void treeLoad(ItemBase itemBase, Map<String, List<ItemBase>> baseMap) {
        List<ItemBase> itemBases = baseMap.get(itemBase.getBaseId());
        if (itemBases == null) {
            return;
        }
        itemBase.setItemBaseSubs(itemBases);
        itemBases.forEach(base->{
            treeLoad(base, baseMap);
        });
    }
}
