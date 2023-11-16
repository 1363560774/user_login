package com.yzl.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzl.service.domain.Item;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * @author kai
 * @date 2023/11/16 17:10
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item>, Serializable {
}
