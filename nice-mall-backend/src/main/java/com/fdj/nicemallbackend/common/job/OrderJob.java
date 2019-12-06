package com.fdj.nicemallbackend.common.job;

import com.fdj.nicemallbackend.common.job.impl.BaseJob;
import com.fdj.nicemallbackend.system.service.IOrderService;
import com.fdj.nicemallbackend.system.service.impl.OrderServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname OrderJob
 * @Description TODO
 * @Date 19-12-6 下午7:43
 * @Created by xns
 */
public class OrderJob implements BaseJob {

    @Autowired
    IOrderService iorderService;

    public OrderJob(){

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
       iorderService.clearOverDueOrders(30);
    }
}
