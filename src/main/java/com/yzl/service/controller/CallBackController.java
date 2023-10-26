package com.yzl.service.controller;

import com.yzl.service.common.ReturnMessage;
import com.yzl.service.dto.CallBackDto;
import com.yzl.service.service.CallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("callBack")
public class CallBackController {

    @Autowired
    private CallBackService callBackService;

    @PostMapping("/describeTaskResponse")
    public ResponseEntity<Object> describeTaskResponse(Integer code, String message, Long requestId, Long appid, Long projectid, String audioUrl, String text, String resultDetail, Double audioTime){
        return ResponseEntity.ok().body(ReturnMessage.successMessage(callBackService.describeTaskResponse()));
    }

    @PostMapping("/tts")
    public ResponseEntity<Object> tts(@RequestBody CallBackDto data){
        return ResponseEntity.ok().body(ReturnMessage.successMessage(data));
    }
}
