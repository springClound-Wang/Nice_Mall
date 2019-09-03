package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.TypeGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-09-02
 */
public interface ITypeGoodsService extends IService<TypeGoods> {

    Map<String,Integer> recoderType(List type);

    void linked(Long goodsId, Map<String, Integer> listId);

    Result getSort();

}
