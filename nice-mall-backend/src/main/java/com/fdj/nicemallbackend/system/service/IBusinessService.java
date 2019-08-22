package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.entity.Business;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
