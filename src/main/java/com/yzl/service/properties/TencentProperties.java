package com.yzl.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 注释
 *
 * @author zhaokai
 * @date 2023/10/26 1:57 下午
 */
@Data
@Component
@ConfigurationProperties(value = "yzl.tencent")
public class TencentProperties {

    private String secretId;
    private String secretKey;

    private String asrEngineModelType;
    private Long asrChannelNum;
    private Long asrResTextFormat;
    private Long asrSourceType;
    private String asrCallbackUrl;

    private Long ttsModelType;
    private String ttsCallbackUrl;
}
