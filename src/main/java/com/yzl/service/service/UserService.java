package com.yzl.service.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yzl.service.common.Page;
import com.yzl.service.domain.Sidebar;
import com.yzl.service.domain.UserInfo;

import java.util.List;

/**
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
public interface UserService extends IService<UserInfo> {

    /**
     * 新增一个用户对象
     * @param userInfo 前端传入用户对象
     * @return 是否添加成功
     */
    Boolean addUserInfo(UserInfo userInfo);

    /**
     * 修改UserInfo对象
     * @param userInfo 用户对象
     * @return 是否修改成功
     */
    Boolean alterUserInfo(UserInfo userInfo);

    /**
     * 根据UserInfoId删除UserInfo对象
     * @param userId 用户id
     * @return 是否删除成功
     */
    Boolean deleteUserInfo(Long userId);

    /**
     * 根据用户名密码获取UserInfo对象
     * @param userId 用户id
     * @param password 密码
     * @return 用户对象
     */
    void checkUserByUserIdAndPassword(String userId, String password);

    /**
     * admin获取UserInfo拥有权限的页面
     * @param selectItem 已选择的侧边栏页面id
     * @param sidebarId 侧边栏id
     * @return 是否成功
     */
    Boolean roleUpdate(String selectItem, String sidebarId);

    /**
     * admin获取UserInfo拥有权限的页面
     * @param sidebarId 侧边栏id
     * @return 是否成功
     */
    Boolean roleDelete(Byte sidebarId);

    /**
     * 创建用户和对应的权限页面
     * @param author 用户名称
     * @param selectItem 侧边栏id
     * @return 是否成功
     */
    Boolean roleCreate(String author, String selectItem);

    Page<UserInfo> userListPage(Page<UserInfo> userInfoPage);

    List<UserInfo> userListByUsername(String username);

    List<Sidebar> selectSidebarList();
}
