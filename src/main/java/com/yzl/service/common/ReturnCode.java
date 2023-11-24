package com.yzl.service.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 返回code
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ReturnCode {

    /**
     * 消息状态
     */
    SUCCESS(1, "成功"),
    AMIS_SUCCESS(0, "成功"),
    BAD_REQUEST(400, "请求出错"),
    NOT_PRESENCE(404, "请求不存在"),
    ERROR(500, "服务器出错"),
    ;
    private int code;

    private String msg;
}
