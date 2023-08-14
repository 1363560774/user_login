package com.yzl.service.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Data
@Component
@ConfigurationProperties("wx.jwt")
public class WxProperties {

    private String appid;
    private String secret;
    private String grant_type;
    private Long expireTime;
    private String wxToken;
    private String weCatAppId;
    private String weCatAppSecret;
    private String weCatRedirectUrl;
    private String cookieName;

    private PublicKey publicKey;
    private PrivateKey privateKey;
}
