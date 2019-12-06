package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.job.OrderJob;
import com.fdj.nicemallbackend.common.job.impl.BaseJob;
import com.fdj.nicemallbackend.system.entity.JobAndTrigger;
import com.fdj.nicemallbackend.system.service.IJobAndTriggerService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname JobController
 * @Description TODO
 * @Date 19-12-6 下午9:12
 * @Created by xns
 */
@Slf4j
@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private IJobAndTriggerService iJobAndTriggerService;

    @Autowired
//    @Qualifier("Scheduler")
    private Scheduler scheduler;


    /**
     * 添加
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping("/addjob")
    public void addjob(@RequestParam(value = "jobClassName")String jobClassName,@RequestParam(value = "jobGroupName")String jobGroupName,@RequestParam(value = "cronExpression")String cronExpression) throws Exception{
        setJob(jobClassName,jobGroupName,cronExpression);
    }

    public void setJob(String jobClassName,String jobGroupName,String cronExpression) throws Exception{
        //启动调度器
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName,jobGroupName).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(jobClassName,jobGroupName);

        try{
            scheduler.scheduleJob(jobDetail,trigger);
        }catch (SchedulerException e){
            log.error("创建定时任务失败");
            throw new Exception("创建定时任务失败");
        }
    }

    /**
     * 查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/queryjob")
    public Map<String,Object> queryjob(@RequestParam(value = "pageNum")Integer pageNum,@RequestParam(value = "pageSize")Integer pageSize){
        PageInfo<JobAndTrigger> jobAndTriggerPageInfo = iJobAndTriggerService.getJobAndTriggerDetails(pageNum,pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("JobAndTrigger",jobAndTriggerPageInfo);
        map.put("number",jobAndTriggerPageInfo.getTotal());
        return map;
    }

    public static BaseJob getClass(String classname) throws Exception{
        Class<?> class1 = Class.forName(classname);
        return (BaseJob)class1.newInstance();
    }
}
