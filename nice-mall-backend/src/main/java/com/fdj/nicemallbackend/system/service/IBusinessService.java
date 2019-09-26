package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.goodsList;
import com.fdj.nicemallbackend.system.entity.Business;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-08-22
 */
public interface IBusinessService extends IService<Business> {

    /**
     * 查看此用户是否已经拥有商铺
     * @param userId
     * @return
     */
    boolean isshop(Long userId);

    /**
     * 判断店铺信息是否满足创建条件，然后创建店铺
     * @param business
     * @return
     */
    Result registerShop(Business business);

    /**
     *
     * @return
     */
    List<goodsList> getGoodsList(Long id);

}
