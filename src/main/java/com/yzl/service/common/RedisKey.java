package com.yzl.service.common;

public class RedisKey {

    public static String loadUserSessionKey(String unionId) {
        return Constant.USER_SESSION_KEY + "_" + unionId;
    }
}
