package com.yzl.service.common;

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
    private Integer status = 0;

    /**
     * 请求成功失败标识 true成功 false失败  默认true
     */
    private Boolean success = Boolean.TRUE;

    /**
     * 请求成功失败枚举结果  默认20000 success
     */
    private Integer code = ReturnCode.SUCCESS.getCode();

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 创建响应对象
     * @param data 返回数据
     * @param status 状态
     * @param returnCode 枚举结果
     * @param <T> 数据类型为泛型
     * @return 消息数据
     */
    private static <T> ReturnMessage<T> createMessage(T data, Integer status, ReturnCode returnCode, Boolean success){
        ReturnMessage<T> returnMessage = new ReturnMessage<>();
        returnMessage.setData(data);
        returnMessage.setStatus(status);
        returnMessage.setSuccess(success);
        returnMessage.setCode(returnCode.getCode());
        returnMessage.setMsg(returnCode.getMsg());
        return returnMessage;
    }

    public static <T> ReturnMessage<T> errorMessage(T data){
        return createMessage(data,1, ReturnCode.ERROR, Boolean.FALSE);
    }

    public static <T> ReturnMessage<T> successMessage(T data){
        return createMessage(data,0, ReturnCode.SUCCESS, Boolean.TRUE);
    }

    public static <T> ReturnMessage<T> errorMessageDate(T data, Integer status){
        return createMessage(data, status, ReturnCode.ERROR, Boolean.FALSE);
    }

    public static <T> ReturnMessage<T> successMessage(){
        return createMessage(null,0, ReturnCode.SUCCESS, Boolean.TRUE);
    }
}
