package com.fdj.nicemallbackend.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @Classname JobAndTrigger
 * @Description TODO
 * @Date 19-12-6 下午9:53
 * @Created by xns
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAndTrigger {
    private String JOB_NAME;

    private String JOB_GROUP;

    private String JOB_CLASS_NAME;

    private String TRIGGER_NAME;

    private String TRIGGER_GROUP;

    private BigInteger REPEAT_INTERVAL;

    private BigInteger TIMES_TRIGGERED;

    private String CRON_EXPRESSION;

    private String TIME_ZONE_ID;
}
