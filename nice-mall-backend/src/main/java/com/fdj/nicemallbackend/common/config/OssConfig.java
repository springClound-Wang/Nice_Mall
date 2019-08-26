package com.fdj.nicemallbackend.common.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname Ossconfig
 * @Description TODO
 * @Date 19-8-26 下午10:40
 * @Created by xns
 */
@Configuration
public class OssConfig {
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;

    @Value("${aliyun.oss.accessKeyId}")
    private String AIYUN_OSS_ACCESSKEYID;

    @Value("${aliyun.oss.accessKeySecret}")
    private String ALIYUN_OSS_ACCESSKEYSECRET;

    @Bean
    public OSSClient ossClient(){
        return new OSSClient(ALIYUN_OSS_ENDPOINT,AIYUN_OSS_ACCESSKEYID,ALIYUN_OSS_ACCESSKEYSECRET);
    }
}
