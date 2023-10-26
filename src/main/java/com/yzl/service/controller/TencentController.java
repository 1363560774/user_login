package com.yzl.service.controller;

import com.yzl.service.common.ReturnMessage;
import com.yzl.service.service.TencentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tencent")
public class TencentController {

    @Autowired
    private TencentService tencentService;

    @PostMapping("/textToVoice")
    public ResponseEntity<Object> askCallBask(String text){
        return ResponseEntity.ok().body(ReturnMessage.successMessage(tencentService.textToVoice(text)));
    }

    @PostMapping("/ttsCallBask")
    public ResponseEntity<Object> ttsCallBask(String url){
        return ResponseEntity.ok().body(ReturnMessage.successMessage(tencentService.voiceToText(url, "", false)));
    }

    @GetMapping("/loadResultByTaskId")
    public ResponseEntity<Object> loadResultByTaskId(Long taskId){
        return ResponseEntity.ok().body(ReturnMessage.successMessage(tencentService.loadResultByTaskId(taskId)));
    }
}
