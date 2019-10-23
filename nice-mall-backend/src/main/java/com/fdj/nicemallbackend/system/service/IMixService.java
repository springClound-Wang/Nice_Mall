package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Result;

import java.util.List;

/**
 * @Classname IMixService
 * @Description TODO
 * @Date 19-10-9 下午7:56
 * @Created by xns
 */
public interface IMixService {
    /**
     * 获取首页的三组大数据
     * @return
     */
    Result getHomePage();

    /**
     * 获取不定数目的推荐商品
     * @return
     */
    List<Findgoods> getFreeRecommend();

}
