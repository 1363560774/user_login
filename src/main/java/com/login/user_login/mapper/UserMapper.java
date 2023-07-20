package com.login.user_login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.login.user_login.domain.UserInfo;
import com.login.user_login.domain.UserRoleRel;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;
import java.util.List;


/**
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Mapper
public interface UserMapper extends BaseMapper<UserInfo>, Serializable {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    @Select("select * from tb_user_info where user_name = #{username}")
    UserInfo checkUserInfoByUserName(@Param("username") String username);

    List<UserInfo> findAll();

    List<UserInfo> selectUserOrganizationName(@Param("userIds") List<Long> userIds);

    @Select("select * from tb_user_info order by create_time asc")
    List<UserInfo> selectListOrderByCreateTimeLimit(@Param("limit") Integer limit);

    List<UserRoleRel> selectUserRoleRel(@Param("userIds") List<Long> userIds);

    @Update("update tb_user_info set user_status = null where user_id = #{userId}")
    Boolean updateUserStatus(@Param("userId") Long userId);
}
