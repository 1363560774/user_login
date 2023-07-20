package com.login.user_login.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 返回数据
 *
 * @author kai
 * @date 2023/07/19 5:28 下午
 */
@Getter
@Setter
@ToString
public class ReturnMessage<T> {

    /**
     * 响应数据
     */
    private T data;

    /**
     * i18ncode 如果有异常建议使用,没有异常默认success
     */
    private String i18nCode = "success";

    /**
     * 请求成功失败标识 true成功 false失败  默认true
     */
    private Boolean flag = Boolean.TRUE;

    /**
     * 请求成功失败枚举结果  默认20000 success
     */
    private Integer code = ReturnCode.SUCCESS.getCode();

    /**
     * 创建响应对象
     * @param data 返回数据
     * @param i18nCode 国际化值
     * @param returnCode 枚举结果
     * @param <T> 数据类型为泛型
     * @return 消息数据
     */
    private static <T> ReturnMessage<T> createMessage(T data, String i18nCode, ReturnCode returnCode, boolean f){
        ReturnMessage<T> returnMessage = new ReturnMessage<>();
        returnMessage.setData(data);
        returnMessage.setI18nCode(i18nCode);
        returnMessage.setCode(returnCode.getCode());
        return returnMessage;
    }

    public static <T> ReturnMessage<T> errorMessage(T data){
        return createMessage(data,"error", ReturnCode.ERROR, Boolean.FALSE);
    }

    public static <T> ReturnMessage<T> SuccessMessage(T data){
        return createMessage(data,"success", ReturnCode.SUCCESS, Boolean.TRUE);
    }

    public static <T> ReturnMessage<T> errorMessageDate(T data, String i18nCode){
        return createMessage(data, i18nCode, ReturnCode.ERROR, Boolean.FALSE);
    }

    public static <T> ReturnMessage<T> SuccessMessage(){
        return createMessage(null,"success", ReturnCode.SUCCESS, Boolean.TRUE);
    }
}
