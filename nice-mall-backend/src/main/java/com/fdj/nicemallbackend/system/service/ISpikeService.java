package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.Spikes;
import com.fdj.nicemallbackend.system.entity.Spike;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-10-03
 */
public interface ISpikeService extends IService<Spike> {

    Result addSpikes(List<Spikes> lists);

//    Result getPartNews(Long goodsId);
}
