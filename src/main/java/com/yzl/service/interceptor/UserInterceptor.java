package com.yzl.service.interceptor;

import com.yzl.service.common.ExceptionEnum;
import com.yzl.service.common.RedisKey;
import com.yzl.service.common.YzlException;
import com.yzl.service.common.utils.CookieUtils;
import com.yzl.service.properties.NonInterceptorProperties;
import com.yzl.service.properties.WxProperties;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @author kai
 * @date 2023/08/14 17:32
 */
public class UserInterceptor implements HandlerInterceptor {

    private WxProperties wxProperties;

    private NonInterceptorProperties nonInterceptorProp;

    private StringRedisTemplate redisTemplate;

    public UserInterceptor(WxProperties prop, NonInterceptorProperties nonInterceptorProp, StringRedisTemplate redisTemplate) {
        this.wxProperties = prop;
        this.nonInterceptorProp = nonInterceptorProp;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 配置过滤校验的路径
        String requestUrl = request.getServletPath();
        if (nonInterceptorProp.getNonInterceptor().contains(requestUrl)) {
            return true;
        }
        // 获取cookie中的unionId
        String unionId = CookieUtils.getCookieValue(request, wxProperties.getCookieName());
        if (StringUtils.isNotBlank(unionId)) {
            String sessionKey = redisTemplate.opsForValue().get(RedisKey.loadUserSessionKey(unionId));
            if (StringUtils.isNotBlank(sessionKey)) {
                String userSessionKey = RedisKey.loadUserSessionKey(unionId);
                redisTemplate.opsForValue().set(userSessionKey, sessionKey);
                redisTemplate.expire(userSessionKey, wxProperties.getExpireTime(), TimeUnit.SECONDS);
                UserContainer.setUnionId(unionId);
                return true;
            }
            throw new YzlException(ExceptionEnum.USER_TOKEN_INVALIDATION);
        }
        throw new YzlException(ExceptionEnum.USER_TOKEN_INVALIDATION);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 移除对象
        UserContainer.remove();
    }
}
