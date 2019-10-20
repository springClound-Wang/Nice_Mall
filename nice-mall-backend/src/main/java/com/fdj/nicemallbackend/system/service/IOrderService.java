package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-10-20
 */
public interface IOrderService extends IService<Order> {

    /**
     * 创建订单
     * @param order
     * @return
     */
    Result createOrder(Order order);
}
