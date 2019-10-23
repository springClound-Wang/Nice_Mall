package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.GoodsDetail;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.TypeEntry;
import com.fdj.nicemallbackend.system.mapper.GoodsMapper;
import com.fdj.nicemallbackend.system.mapper.SpikeMapper;
import com.fdj.nicemallbackend.system.mapper.TypeEntryMapper;
import com.fdj.nicemallbackend.system.service.IGoodsService;
import com.fdj.nicemallbackend.system.service.IMixService;
import com.fdj.nicemallbackend.system.service.ISpikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Classname MixServiceImpl
 * @Description TODO
 * @Date 19-10-9 下午7:57
 * @Created by xns
 */
@Service
public class MixServiceImpl implements IMixService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    SpikeMapper spikeMapper;

    @Autowired
    TypeEntryMapper typeEntryMapper;

    private List<Findgoods> getRecommend(Integer num){
        Integer i = goodsMapper.getCount();
        Random rand = new Random();
        if(i<num){
            i=num;
        }
        List<Findgoods> recommendLists = goodsMapper.selectLimit(rand.nextInt(i-num),num);
        return recommendLists;
    }
    /**
     * 获取首页的三大组数据,此处因为前端界面的局限，所以显示的三条秒杀商品有可能已经过期
     * @return
     */
    @Override
    public Result getHomePage() {
        Map map = new HashMap<>();
        Random rand = new Random();
        List<Findgoods> recommendLists = getRecommend(3);
        map.put("recommendList",recommendLists);

        List<TypeEntry> typeEntry = typeEntryMapper.selectAll();
        map.put("typeEntry",typeEntry);

        List<Findgoods> spike = new ArrayList<>();

        Integer j = spikeMapper.getCount();
        if(j>=3) {
            spike = spikeMapper.selectLimit(rand.nextInt(j-3), 3);
        }else{
            return new Result().fail(map,"秒杀商品数据太少");
        }
        map.put("spikeList",spike);
        return new Result().success(map,"查询成功!");
    }

    /**
     * 获取随机商品推荐
     * @return
     */
    @Override
    public List<Findgoods> getFreeRecommend() {
        return getRecommend(5);
    }
}
