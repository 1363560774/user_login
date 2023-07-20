package com.login.user_login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.login.user_login.domain.Sidebar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * 注释
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Mapper
public interface SidebarMapper extends BaseMapper<Sidebar>, Serializable {

    @Select("SELECT us.* FROM tb_user_sidebar us INNER JOIN tb_user_role_sidebar_rel ursr ON ursr.sidebar_id = us.sidebar_id INNER JOIN tb_user_role ur ON ur.role_id = ursr.role_id WHERE ur.role_id = #{roleId}")
    List<Sidebar> selectRoleSidebar(@Param("roleId") Byte roleId);
}
