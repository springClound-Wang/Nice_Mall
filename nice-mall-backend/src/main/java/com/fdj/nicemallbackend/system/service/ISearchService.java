package com.fdj.nicemallbackend.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fdj.nicemallbackend.system.dto.Items;
import com.fdj.nicemallbackend.system.entity.Goods;

/**
 * @Classname ISearchService
 * @Description TODO
 * @Date 19-10-30 下午5:11
 * @Created by xns
 */
public interface ISearchService {


    public Items buildGoods(Goods goods) throws JsonProcessingException;
}
