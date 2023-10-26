package com.yzl.service.service;

import com.yzl.service.dto.CallBackDto;

/**
 * 注释
 *
 * @author kai
 * @date 2023/10/25 6:48 下午
 */
public interface CallBackService {

    /**
     * ask 语音回调
     * @return 语音结果
     */
    String asrCallBack(Integer code, String message, Long requestId, Long appid, Long projectId, String audioUrl, String text, String resultDetail, Double audioTime);

    /**
     * tts 语音回调
     * @return 语音结果
     */
    String ttsCallBack(CallBackDto callBackDto);
}
