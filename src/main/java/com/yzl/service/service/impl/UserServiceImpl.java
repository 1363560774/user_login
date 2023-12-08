package com.yzl.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzl.service.common.Constant;
import com.yzl.service.common.Page;
import com.yzl.service.domain.*;
import com.yzl.service.mapper.*;
import com.yzl.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private TDesignMenuMapper tDesignMenuMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional
    public Boolean addUserInfo(UserInfo userInfo) {
        String userId = IdWorker.getIdStr();
        userInfo.setUserId(userId);
        userInfo.setUserStatus((short) 0);
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
    public Boolean deleteUserInfo(String userId) {
        return userMapper.updateUserStatus(userId);
    }

    @Override
    public UserInfo checkUserByUserIdAndPassword(String loginName, String password) {
        Login login = loginMapper.selectOne(
                Wrappers.<Login>lambdaQuery()
                        .eq(Login::getLoginName, loginName)
                        .eq(Login::getPassword, password));
        return userMapper.selectById(login.getLoginId());
    }

    @Override
    public Boolean roleUpdate(String selectItem, String sidebarId) {
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
        userInfo.setUserId(IdWorker.getIdStr());
        userInfo.setUsername(author);
        userInfo.setEmail("1363560774@qq.com");
        Sidebar sidebar = new Sidebar();
        sidebar.setSidebarId("222");
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
                        .eq(userInfoPage.getSex() != null,
                                UserInfo::getSex, userInfoPage.getSex())
                        .isNotNull(UserInfo::getUserStatus)
                        .in(UserInfo::getUserStatus, 0, 1)
                        .and(StringUtils.isNotEmpty(userInfoPage.getUsername()),
                                wrapper -> wrapper.like(UserInfo::getUsername, userInfoPage.getUsername())
                                        .or().eq(StringUtils.isNotEmpty(userInfoPage.getUsername()),
                                                UserInfo::getUserId, userInfoPage.getUsername())));
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

    @Override
    public List<Sidebar> selectSidebarList() {
        return sidebarMapper.selectRoleSidebar();
    }

    @Override
    public UserInfo loadUserInfoById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public List<Menu> loadMenu() {
        List<Menu> menus = menuMapper.selectList(Wrappers.lambdaQuery());
        Map<Integer, List<Menu>> menuMap = menus.stream()
                .collect(Collectors.groupingBy(Menu::getPid));
        List<Menu> menusList = menuMap.get(0);
        menusList.forEach(menu -> {
            menusTreeLoad(menu, menuMap);
        });
        return menusList;
    }

    @Override
    public List<TDesignMenu> loadTdMenu() {
        List<TDesignMenu> tDesignMenus = tDesignMenuMapper.selectList(Wrappers.lambdaQuery());
        if (tDesignMenus.isEmpty()) {
            return new ArrayList<>(0);
        }
        Map<Integer, List<TDesignMenu>> tdMenuMap = tDesignMenus.stream()
                .collect(Collectors.groupingBy(TDesignMenu::getPid));
        List<TDesignMenu> menusList = tdMenuMap.get(0);
        menusList.forEach(menu -> {
            tdMenusTreeLoad(menu, tdMenuMap);
        });
        return menusList;
    }

    private void menusTreeLoad(Menu menu, Map<Integer, List<Menu>> menuMap) {
        List<Menu> menus = menuMap.get(menu.getId());
        if (menus == null) {
            return;
        }
        menus.sort(Comparator.comparing(Menu::getShowOrder));
        menu.setChildren(menus);
        menus.forEach(base-> menusTreeLoad(base, menuMap));
    }

    private void tdMenusTreeLoad(TDesignMenu menu, Map<Integer, List<TDesignMenu>> menuMap) {
        TDesignMenu.Meta meta = new TDesignMenu.Meta();
        meta.setIcon(menu.getIcon());
        TDesignMenu.Language language = new TDesignMenu.Language();
        language.setZh_CN(menu.getCnTitle());
        language.setEn_US(menu.getUsTitle());
        meta.setTitle(language);
        menu.setMeta(meta);
        List<TDesignMenu> menus = menuMap.get(menu.getId());
        if (menus == null) {
            return;
        }
        menus.sort(Comparator.comparing(TDesignMenu::getShowOrder));
        menu.setChildren(menus);
        menus.forEach(base-> tdMenusTreeLoad(base, menuMap));
    }
}
