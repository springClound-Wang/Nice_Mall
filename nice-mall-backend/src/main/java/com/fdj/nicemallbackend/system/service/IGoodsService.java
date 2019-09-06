package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-08-31
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 保存衣服类型商品
     * @param map
     */
    Result saveToclothes(Map<String, Object> map);

    /**
     * 鞋类型的商品
     * @param map
     */
    Result saveToshoes(Map<String, Object> map);

    /**
     * 包商品
     * @param map
     */
    Result saveTopackage(Map<String, Object> map);


    /**
     * 电子产品
     * @param map
     */
    Result saveToelectr(Map<String, Object> map);

    /**
     * 根据字段模糊查询商品
     * @param field
     * @return
     */
    Set<Findgoods> findByField(String field);
}
