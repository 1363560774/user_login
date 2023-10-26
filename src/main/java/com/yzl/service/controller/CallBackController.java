package com.yzl.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.yzl.service.common.ReturnMessage;
import com.yzl.service.dto.CallBackDto;
import com.yzl.service.service.CallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("callBack")
public class CallBackController {

    @Autowired
    private CallBackService callBackService;

    @PostMapping("/asrCallBask")
    public ResponseEntity<Object> askCallBask(Integer code, String message, Long requestId, Long appid, Long projectid, String audioUrl, String text, String resultDetail, Double audioTime){
        return ResponseEntity.ok().body(ReturnMessage.successMessage(callBackService.asrCallBack(
                code, message, requestId, appid, projectid, audioUrl, text, resultDetail, audioTime)));
    }

    @PostMapping("/ttsCallBask")
    public ResponseEntity<Object> ttsCallBask(String data){
        CallBackDto callBackDto = JSONObject.parseObject(data, CallBackDto.class);
        return ResponseEntity.ok().body(ReturnMessage.successMessage(callBackService.ttsCallBack(callBackDto)));
    }
}
