package com.fdj.nicemallbackend.system.entity;

import lombok.Data;

import java.math.BigInteger;

/**
 * @Classname JobAndTrigger
 * @Description TODO
 * @Date 19-12-6 下午9:53
 * @Created by xns
 */
@Data
public class JobAndTrigger {
    private String JOB_NAME;

    private String Job_GROUP;

    private String JOB_CLASS_NAME;

    private String TRIGGER_NAME;

    private String TRIGGER_GROUP;

    private BigInteger REPEAT_INTERVAL;

    private BigInteger TIMES_TRIGGERED;

    private String CRON_EXPRESSION;

    private String TIME_ZONE_ID;
}
