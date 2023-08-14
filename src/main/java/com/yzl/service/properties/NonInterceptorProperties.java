package com.yzl.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 注释
 *
 * @author kai
 * @date 2023/08/14 15:26
 */
@Data
@Component
@ConfigurationProperties(prefix = "yzl.interceptor")
public class NonInterceptorProperties {
    /**
     * 不拦截的路径
     */
    private List<String> nonInterceptor;
}
