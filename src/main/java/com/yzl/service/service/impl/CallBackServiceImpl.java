package com.yzl.service.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yzl.service.dto.CallBackDto;
import com.yzl.service.service.CallBackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author kai
 * @date 2023/10/25 6:48 下午
 */
@Slf4j
@Service
public class CallBackServiceImpl implements CallBackService {

    @Override
    public String asrCallBack(Integer code, String message, Long requestId,
                              Long appid, Long projectId, String audioUrl,
                              String text, String resultDetail, Double audioTime) {
        log.info("code={}, message={}, requestId={}, appid={}, projectId={}, audioUrl={}, text={}, resultDetail={}, audioTime={}",
                code, message, requestId, appid, projectId, audioUrl, text, resultDetail, audioTime);
        return null;
    }

    @Override
    public String ttsCallBack(CallBackDto callBackDto) {
        log.info(JSONObject.toJSONString(callBackDto));
        return null;
    }
}