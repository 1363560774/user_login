package com.yzl.service.common;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;


/**
 * @author kai
 * @date 2023/08/14 17:32
 */
@Getter
@Setter
public class ExceptionResult {
    private int status;
    private String message;
    private String timestamp;

     public ExceptionResult(YzlException e) {
        this.status = e.getStatus();
        this.message = e.getMessage();
        this.timestamp = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
    }
}
