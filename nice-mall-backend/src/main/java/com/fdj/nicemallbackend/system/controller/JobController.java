package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.job.impl.BaseJob;
import com.fdj.nicemallbackend.system.entity.JobAndTrigger;
import com.fdj.nicemallbackend.system.service.IJobAndTriggerService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
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
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping("/addjob")
    public void addjob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName, @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        setJob(jobClassName, jobGroupName, cronExpression);
    }

    /**
     * com.fdj.nicemallbackend.common.job.OrderJob
     * 0 0/10 * ? * * 每10分钟执行一次
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    public void setJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
        //启动调度器
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(scheduleBuilder).build();

        try{
        scheduler.scheduleJob(jobDetail, trigger);
        }catch (SchedulerException e){
            log.error("创建定时任务失败");
            throw new Exception("创建定时任务失败");
        }
    }

    /**
     * 查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value = "pageNum") Integer pageNum, @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<JobAndTrigger> jobAndTriggerPageInfo = iJobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("JobAndTrigger", jobAndTriggerPageInfo);
        map.put("number", jobAndTriggerPageInfo.getTotal());
        return map;
    }

    /**
     * 暂停
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping("/pausejob")
    public void pausejob(@RequestParam("jobClassName") String jobClassName,@RequestParam("jobGroupName")String jobGroupName) throws Exception {
        jobPause(jobClassName,jobGroupName);
    }

    public void jobPause(String jobClassName,String jobGroupName) throws Exception{
        scheduler.pauseJob(JobKey.jobKey(jobClassName,jobGroupName));
    }

    /**
     * 恢复
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping("/resumejob")
    public void resumejob(@RequestParam("jobClassName") String jobClassName,@RequestParam("jobGroupName") String jobGroupName) throws Exception{
        jobresume(jobClassName,jobGroupName);
    }

    public void jobresume(String jobClassName,String jobGroupName) throws Exception{
        scheduler.resumeJob(JobKey.jobKey(jobClassName,jobGroupName));
    }


    /**
     * 修改
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping("/reschedulejob")
    public void reschedule(@RequestParam("jobClassName")String jobClassName,@RequestParam("jobGroupName")String jobGroupName,@RequestParam("cronExpression")String cronExpression) throws Exception{
        jobreschedule(jobClassName,jobGroupName,cronExpression);
    }

    public void jobreschedule(String jobClassName,String jobGroupName,String cronExpression) throws Exception{
        try{
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName,jobGroupName);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);

            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            scheduler.rescheduleJob(triggerKey,trigger);
        }catch (SchedulerException e){
            log.error("更新定时任务失败");
            throw new Exception("更新定时任务失败");
        }
    }

    /**
     * 删除某job
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping("/deletejob")
    public void deletejob(@RequestParam("jobClassName")String jobClassName,@RequestParam("jobGroupName")String jobGroupName)throws Exception{
        jobdelete(jobClassName,jobGroupName);
    }
    public void jobdelete(String jobClassName,String jobGroupName) throws Exception{
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName,jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName,jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobClassName,jobGroupName));
    }


    public static BaseJob getClass(String classname) throws Exception {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob) class1.newInstance();
    }
}
