package com.login.user_login.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.user_login.common.Constant;
import com.login.user_login.common.Page;
import com.login.user_login.domain.*;
import com.login.user_login.mapper.LoginMapper;
import com.login.user_login.mapper.SidebarMapper;
import com.login.user_login.mapper.UserMapper;
import com.login.user_login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SidebarMapper sidebarMapper;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    @Transactional
    public Boolean addUserInfo(UserInfo userInfo) {
        long userId = IdWorker.getId();
        userInfo.setUserId(userId);
        userInfo.setUserStatus(Boolean.FALSE);
        userInfo.setCreateTime(new Date());
        UserRoleRel userRoleRel = new UserRoleRel();
        userRoleRel.setUserId(userId);
        userRoleRel.setRoleId(userInfo.getRoleId());
        int insert = userMapper.insert(userInfo);
        return insert > Constant.ZERO;
    }

    @Override
    public Boolean alterUserInfo(UserInfo userInfo) {
        return userMapper.update(userInfo,
                Wrappers.<UserInfo>lambdaUpdate()
                        .eq(UserInfo::getUserId, userInfo.getUserId())) > Constant.ZERO;
    }

    @Override
    public Boolean deleteUserInfo(Long userId) {
        return userMapper.updateUserStatus(userId);
    }

    @Override
    public void checkUserByUserIdAndPassword(String userId, String password) {
        Login login = loginMapper.selectOne(
                Wrappers.<Login>lambdaQuery()
                        .eq(Login::getLoginName, userId)
                        .eq(Login::getPasswd, password));
        UserInfo userInfo = userMapper.selectById(userId);
    }

    @Override
    public Boolean roleUpdate(String selectItem, Byte sidebarId) {
        Sidebar sidebar = new Sidebar();
        sidebar.setSidebarId(sidebarId);
        return sidebarMapper.updateById(sidebar) > Constant.ZERO;
    }

    @Override
    public Boolean roleDelete(Byte sidebarId) {
        return sidebarMapper.deleteById(sidebarId) > Constant.ZERO;
    }

    @Override @Transactional
    public Boolean roleCreate(String author, String selectItem) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(IdWorker.getId());
        userInfo.setUsername(author);
        userInfo.setEmail("1363560774@qq.com");
        Sidebar sidebar = new Sidebar();
        sidebar.setSidebarId((byte) 222);
        Boolean u = userMapper.insert(userInfo) > Constant.ZERO;
        Boolean s = sidebarMapper.insert(sidebar) > Constant.ZERO;
        return u && s;
    }

    @Override
    public Page<UserInfo> userListPage(Page<UserInfo> userInfoPage) {
        Page<UserInfo> page = new Page<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time");
        orderItem.setAsc(false);
        page.addOrder(orderItem);
        page.setCurrent(userInfoPage.getCurrent());
        page.setSize(userInfoPage.getSize());
        return userMapper.selectPage(page,
                Wrappers.<UserInfo>lambdaQuery()
                        .eq(StringUtils.isNotEmpty(userInfoPage.getStatus()),
                                UserInfo::getUserStatus, userInfoPage.getStatus())
                        .isNotNull(UserInfo::getUserStatus)
                        .and(StringUtils.isNotEmpty(userInfoPage.getFilter()),
                                wrapper -> wrapper.like(UserInfo::getUsername, userInfoPage.getFilter())
                                        .or().eq(StringUtils.isNotEmpty(userInfoPage.getFilter()),
                                                UserInfo::getUserId, userInfoPage.getFilter())));
    }

    @Override
    public List<UserInfo> userListByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
           return userMapper.selectListOrderByCreateTimeLimit(10);
        }
        return userMapper.selectList(Wrappers.<UserInfo>lambdaQuery()
                .isNotNull(UserInfo::getUserStatus)
                .like(StringUtils.isNotEmpty(username), UserInfo::getUsername, username));
    }
}
