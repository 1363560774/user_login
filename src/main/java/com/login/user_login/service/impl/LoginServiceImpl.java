package com.login.user_login.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.user_login.domain.Login;
import com.login.user_login.mapper.LoginMapper;
import com.login.user_login.service.LoginService;
import com.login.user_login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注释
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements LoginService {

    @Autowired
    private UserService userService;

//    @Autowired
//    private SidebarMapper sidebarMapper;
//
//    @Autowired
//    private RoleMapper roleMapper;

    /**
     * 登录入口
     * @param login 用户名信息
     * @return 登录信息
     */
    @Override
    public String login(Login login) {
        String newPassword = "CodecUtils.md5Hex(userLogin.getPasswd(), jwtProp.getSalt())";
        //校验用户
        userService.checkUserByUserIdAndPassword("", newPassword);
//        log.info("登录成功{}", login.getUserId());
//        UserRole userRole = roleMapper.selectMaxUserRole(login.getUserId());
//        login.setRole(userRole.getRoleType());
//        UserContainer.setUser(login);
//        // 利用JWT工具，生成token返回
//        return JwtUtils.generateToken(login, jwtProp.getPrivateKey(), jwtProp.getExpire());
        return newPassword;
    }
}
