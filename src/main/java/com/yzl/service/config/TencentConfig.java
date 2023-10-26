package com.yzl.service.config;

import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.nlp.v20190408.NlpClient;
import com.tencentcloudapi.soe.v20180724.SoeClient;
import com.tencentcloudapi.tts.v20190823.TtsClient;
import com.yzl.service.properties.TencentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TencentConfig {

    @Autowired
    private TencentProperties properties;

    /**
     * 语音合成（Text To Speech，TTS）
     */
    String ENDPOINT_TTS = "tts.tencentcloudapi.com";

    /***
     * 语音识别（Automatic Speech Recognition，ASR）
     */
    String ENDPOINT_ASR = "asr.tencentcloudapi.com";

    /**
     * 自然语言处理（Natural Language Process，NLP）
     */
    String ENDPOINT_NLP = "nlp.tencentcloudapi.com";

    /**
     * 智聆口语评测（Smart Oral Evaluation，SOE）
     */
    String ENDPOINT_SOE = "soe.tencentcloudapi.com";

    /**
     * Region("ap-beijing");
     */
    String REGION_BEIJING = "ap-beijing";

    /**
     * Region("ap-shanghai");
     */
    String REGION_SHANGHAI = "ap-shanghai";

    /**
     * Region("ap-guangzhou");
     */
    String REGION_GUANGZHOU = "ap-guangzhou";

    @Bean("ttsClient")
    TtsClient ttsClient() {
        Credential cred = new Credential(properties.getSecretId(), properties.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(ENDPOINT_TTS);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        clientProfile.setDebug(false);
        return new TtsClient(cred, REGION_SHANGHAI, clientProfile);
    }

    @Bean("asrClient")
    AsrClient asrClient() {
        Credential cred = new Credential(properties.getSecretId(), properties.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(ENDPOINT_ASR);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new AsrClient(cred, REGION_SHANGHAI, clientProfile);
    }

    @Bean("nlpClient")
    NlpClient nlpClient() {
        Credential cred = new Credential(properties.getSecretId(), properties.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(ENDPOINT_NLP);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new NlpClient(cred, REGION_GUANGZHOU, clientProfile);
    }

    @Bean("soeClient")
    SoeClient soeClient() {
        Credential cred = new Credential(properties.getSecretId(), properties.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(ENDPOINT_SOE);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        return new SoeClient(cred, "", clientProfile);
    }
}
