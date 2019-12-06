package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.JobAndTrigger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname JobAndTriggerMapper
 * @Description TODO
 * @Date 19-12-6 下午10:01
 * @Created by xns
 */
@Mapper
public interface JobAndTriggerMapper {

    public List<JobAndTrigger> getJobAndTriggerDetails();
}
