package com.fdj.nicemallbackend.common.job.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Classname BaseJob
 * @Description TODO
 * @Date 19-12-6 下午9:40
 * @Created by xns
 */
public interface BaseJob extends Job {
    /**
     * 继承
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException;
}
