package com.yzl.service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author kai
 * @date 2023/08/14 17:32
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    /**
     * 异常状态
     */
    USER_TOKEN_INVALIDATION(403, "登录失效,请重新登录"),
    USER_NAME_NOT_EXIST(400, "用户不存在"),
    USER_NAME_PASSWORD_ERROR(400, "用户名密码错误"),
    USER_STATUS_FREEZE(400, "用户被冻结"),
    ERROR_PASSWORD(400, "密码错误"),
    ;
    private int status;

    private String msg;
}
