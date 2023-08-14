package com.yzl.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzl.service.common.RedisKey;
import com.yzl.service.common.utils.RestTemplate;
import com.yzl.service.domain.Login;
import com.yzl.service.dto.WxDto;
import com.yzl.service.mapper.LoginMapper;
import com.yzl.service.properties.WxProperties;
import com.yzl.service.service.LoginService;
import com.yzl.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private WxProperties wxProperties;

    @Autowired
    private StringRedisTemplate redisTemplate;

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

    @Override
    public WxDto xcxLogin(String jsCode) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
        url = String.format(url, wxProperties.getAppid(), wxProperties.getSecret(), jsCode);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();
        String resp = RestTemplate.getRestTemplate().getForObject(uri, String.class);
        if (resp != null && resp.contains("openid")) {
            WxDto wxDto = JSONObject.parseObject(resp, WxDto.class);
            String userSessionKey = RedisKey.loadUserSessionKey(wxDto.getUnionId());
            redisTemplate.opsForValue().set(userSessionKey, wxDto.getSessionKey());
            redisTemplate.expire(userSessionKey, wxProperties.getExpireTime(), TimeUnit.SECONDS);
            return wxDto;
        }
        return null;
    }
}
