package com.yzl.service.service.impl;

import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskRequest;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskResponse;
import com.tencentcloudapi.asr.v20190614.models.DescribeTaskStatusRequest;
import com.tencentcloudapi.asr.v20190614.models.DescribeTaskStatusResponse;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.tts.v20190823.TtsClient;
import com.tencentcloudapi.tts.v20190823.models.CreateTtsTaskRequest;
import com.tencentcloudapi.tts.v20190823.models.CreateTtsTaskResponse;
import com.yzl.service.properties.TencentProperties;
import com.yzl.service.service.TencentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TencentServiceImpl implements TencentService {

    @Autowired
    private TencentProperties properties;

    @Autowired
    private AsrClient asrClient;

    @Autowired
    private TtsClient ttsClient;

    @Override
    public String voiceToText(String url, String data, Boolean sourceData) {
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            CreateRecTaskRequest req = new CreateRecTaskRequest();
            req.setEngineModelType(properties.getAsrEngineModelType());
            req.setChannelNum(properties.getAsrChannelNum());
            req.setResTextFormat(properties.getAsrResTextFormat());
            req.setSourceType(properties.getAsrSourceType());
            req.setCallbackUrl(properties.getAsrCallbackUrl());
            if (sourceData && StringUtils.isNotBlank(data)) {
                req.setData(data);
            } else {
                req.setUrl(url);
            }
            // 返回的resp是一个CreateRecTaskResponse的实例，与请求对象对应
            CreateRecTaskResponse resp = asrClient.CreateRecTask(req);
            log.info(CreateRecTaskResponse.toJsonString(resp));
            return CreateRecTaskResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            log.error(e.toString());
        }
        return null;
    }

    public String loadResultByTaskId(Long taskId) {
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DescribeTaskStatusRequest req = new DescribeTaskStatusRequest();
            req.setTaskId(taskId);
            // 返回的resp是一个DescribeTaskStatusResponse的实例，与请求对象对应
            DescribeTaskStatusResponse resp = asrClient.DescribeTaskStatus(req);
            // 输出json格式的字符串回包
            log.info(DescribeTaskStatusResponse.toJsonString(resp));
            return DescribeTaskStatusResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            log.error(e.toString());
        }
        return null;
    }

    public String textToVoice(String text) {
        try{
            // 实例化一个请求对象,每个接口都会对应一个request对象
            CreateTtsTaskRequest req = new CreateTtsTaskRequest();
            req.setText(text);
            req.setModelType(properties.getTtsModelType());
            req.setCallbackUrl(properties.getTtsCallbackUrl());
            // 返回的resp是一个CreateTtsTaskResponse的实例，与请求对象对应
            CreateTtsTaskResponse resp = ttsClient.CreateTtsTask(req);
            // 输出json格式的字符串回包
            log.info(CreateTtsTaskResponse.toJsonString(resp));
            return CreateTtsTaskResponse.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            log.error(e.toString());
        }
        return null;
    }
}
