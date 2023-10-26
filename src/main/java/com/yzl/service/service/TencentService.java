package com.yzl.service.service;

public interface TencentService {

    /**
     * 语音转文本
     * @param url 语音路径
     * @param data 语音文件
     * @param sourceData 输入源类型  true-> 语音文件方式，需要转base64, false-> 链接方式
     * @return 结果
     */
    String voiceToText(String url, String data, Boolean sourceData);

    /**
     * 文本转语音
     * @param text 文本
     * @return 结果
     */
    String textToVoice(String text);

    /**
     * 根据taskId获取数据
     * @param taskId 任务id
     * @return 结果
     */
    String loadResultByTaskId(Long taskId);
}
