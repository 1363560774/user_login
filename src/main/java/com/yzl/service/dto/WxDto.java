package com.yzl.service.dto;

import lombok.Data;

@Data
public class WxDto {

    private String openId;
    private String unionId;
    private String sessionKey;
    private String refreshToken;
}
