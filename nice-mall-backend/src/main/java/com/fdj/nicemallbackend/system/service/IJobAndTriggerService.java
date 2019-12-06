package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.entity.JobAndTrigger;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * @Classname IJobAndTriggerService
 * @Description TODO
 * @Date 19-12-6 下午9:49
 * @Created by xns
 */
public interface IJobAndTriggerService {

    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
