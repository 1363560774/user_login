package com.yzl.service.interceptor;

import com.yzl.service.properties.NonInterceptorProperties;
import com.yzl.service.properties.WxProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注释
 *
 * @author zhaokai
 * @date 2020/2/25 11:26 下午
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private WxProperties wxProperties;

    @Autowired
    private NonInterceptorProperties nonInterceptorProp;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor(wxProperties, nonInterceptorProp, redisTemplate)).addPathPatterns("/**");
    }
}
