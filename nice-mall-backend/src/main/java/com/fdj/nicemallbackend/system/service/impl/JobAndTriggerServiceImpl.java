package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.entity.JobAndTrigger;
import com.fdj.nicemallbackend.system.mapper.JobAndTriggerMapper;
import com.fdj.nicemallbackend.system.service.IJobAndTriggerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname JobAndTriggerServiceImpl
 * @Description TODO
 * @Date 19-12-6 下午9:49
 * @Created by xns
 */
@Service
public class JobAndTriggerServiceImpl implements IJobAndTriggerService {

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;


    @Override
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
        PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
        return page;
    }
}
