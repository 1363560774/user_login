package com.yzl.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzl.service.domain.Login;
import com.yzl.service.dto.WxDto;

/**
 * 注释
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
public interface LoginService extends IService<Login> {

    /**
     * 登录入口
     * @param userLogin 用户信息
     * @return 登录状态
     */
    String login(Login userLogin);

    /**
     * 小程序登陆
     * @param jsCode 微信登陆code
     * @param loginIp 登陆ip地址
     * @return 登陆鉴权
     */
    WxDto xcxLogin(String jsCode, String loginIp);
}
