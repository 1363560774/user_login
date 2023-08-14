package com.yzl.service.common;

import lombok.Getter;

/**
 * @author kai
 * @date 2023/08/14 17:32
 */
@Getter
public class YzlException extends RuntimeException{

    private final int status;

    public YzlException(ExceptionEnum e) {
        super(e.getMsg());
        this.status = e.getStatus();
    }

    public YzlException(ExceptionEnum e, Throwable cause ) {
        super(e.getMsg(), cause);
        this.status = e.getStatus();
    }
}
