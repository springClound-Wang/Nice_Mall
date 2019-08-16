package com.fdj.nicemallbackend.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Classname HttpContextUtil
 * @Description TODO
 * @Date 19-8-15 上午11:18
 * @Created by xns
 */
public class HttpContextUtil {
    private HttpContextUtil(){

    }
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
