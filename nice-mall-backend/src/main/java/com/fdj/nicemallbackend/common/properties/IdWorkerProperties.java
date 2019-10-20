package com.fdj.nicemallbackend.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname IdWorkerProperties
 * @Description TODO
 * @Date 19-10-19 下午7:03
 * @Created by xns
 */
@Configuration
@ConfigurationProperties(prefix = "nice.worker")
public class IdWorkerProperties {

    /**
     * 当前机器id
     */
    private long workerId;

    /**
     * 序列号
     */
    private long datacenterId;

    public long getWorkerId(){
        return workerId;
    }

    public void setWorkerId(long workerId){
        this.workerId=workerId;
    }

    public long getDatacenterId(){
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId){
        this.datacenterId=datacenterId;
    }
}
