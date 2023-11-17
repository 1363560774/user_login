package com.yzl.service.mapper;

import com.yzl.service.domain.ItemBase;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * @author kai
 * @date 2023/11/16 17:10
 */
@Mapper
public interface ItemBaseMapper extends com.baomidou.mybatisplus.core.mapper.BaseMapper<ItemBase>, Serializable {

    @Select("WITH RECURSIVE ItemTree AS (\n" +
            "    SELECT base_id, parent_id, base_name, show_order, create_time, update_time\n" +
            "    FROM tb_item_base\n" +
            "    WHERE base_id = #{baseId}\n" +
            "    UNION ALL\n" +
            "    SELECT c.base_id, c.parent_id, c.base_name, c.show_order, c.create_time, c.update_time\n" +
            "    FROM tb_item_base c\n" +
            "    JOIN ItemTree p ON p.base_id = c.parent_id\n" +
            ")SELECT * FROM ItemTree;")
    List<ItemBase> selectItemBaseTree(@Param("baseId") String baseId);
}
