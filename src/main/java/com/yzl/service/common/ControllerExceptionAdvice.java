package com.yzl.service.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * @author kai
 * @date 2023/08/14 17:32
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(YzlException.class)
    public ResponseEntity<ExceptionResult> handleLyException(YzlException e){
        //从异常中获取状态码和提示信息
        return ResponseEntity.status(e.getStatus()).body(new ExceptionResult(e));
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e){
        log.error("服务异常:{1}", e);
        //我们暂定返回状态码为400， 然后从异常中获取友好提示信息
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
