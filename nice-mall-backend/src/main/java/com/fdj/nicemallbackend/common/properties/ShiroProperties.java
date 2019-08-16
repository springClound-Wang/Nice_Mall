package com.fdj.nicemallbackend.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname SiroProperties
 * @Description TODO
 * @Date 19-8-14 下午9:06
 * @Created by xns
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {

    /**
     * 免认证 URL
     */
    private String anonUrl;
    /**
     * token有效时间1小时
     */
    private Long jwtTimeOut;

}
