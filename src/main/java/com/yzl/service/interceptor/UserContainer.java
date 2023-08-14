package com.yzl.service.interceptor;

/**
 * @author kai
 * @date 2023/08/14 15:41
 */
public class UserContainer {

    private static final ThreadLocal<String> TL = new ThreadLocal<>();

    public static String getUnionId(){
        return TL.get();
    }
    public static void setUnionId(String unionId){
        TL.set(unionId);
    }
    public static void remove(){
        TL.remove();
    }
}
