package com.yzl.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzl.service.domain.Login;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * 注释
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Mapper
public interface LoginMapper extends BaseMapper<Login>, Serializable {
}
