package com.login.user_login.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties("wx.jwt")
public class WxProperties {

    private String wxToken;
    private String weCatAppId;
    private String weCatAppSecret;
    private String weCatRedirectUrl;

    private PublicKey publicKey;
    private PrivateKey privateKey;
}
