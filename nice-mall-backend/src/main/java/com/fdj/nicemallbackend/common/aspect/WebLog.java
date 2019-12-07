package com.fdj.nicemallbackend.common.aspect;

import com.xiaoleilu.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Classname WebLog
 * @Description TODO
 * @Date 19-12-4 下午12:21
 * @Created by xns
 */
@Slf4j
@Aspect
@Component
public class WebLog {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     *  表示匹配com.fdj.nicemallbackend.system.controller包及其子包下的所有方法
     */
    @Pointcut("execution(* com.fdj.nicemallbackend.system.controller..*.*(..))")
    public void webLog(){}

    /**
     * 前置通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        /**
         * 接收到请求后记录请求内容
         */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        /**
         * 记录下请求内容
         */
        log.info("<====================================================================");
        log.info("请求URL: => " + request.getRequestURL().toString());
        log.info("请求方式: " + request.getMethod());
        log.info("请求来源IP: " + request.getRemoteAddr());
        log.info("响应方法: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数: " + Arrays.toString(joinPoint.getArgs()));
        log.info("---------------------------------------------------------------------");
        startTime.set(System.currentTimeMillis());
    }

    /**
     * 后置
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret)throws Throwable{
        if(ret == null){
            log.info("====================================================================>");
        }else {
            if (!ret.getClass().equals(String.class)) {
                log.info("返回: " + JSONUtil.toJsonPrettyStr(ret));
                log.info("耗时(毫秒): " + (System.currentTimeMillis() - startTime.get()));
                log.info("====================================================================>");
            }
        }
    }
}
