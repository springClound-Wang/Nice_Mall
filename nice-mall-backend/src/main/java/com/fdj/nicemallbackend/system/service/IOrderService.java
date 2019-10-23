package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.orderDto;
import com.fdj.nicemallbackend.system.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /**
     * 下单
     * @param orderId
     * @param orderStatus
     * @return
     */
    Result updateOrderStatus(Long orderId, Integer orderStatus);

    /**
     * 查询所有订单
     * @return
     */
    List<orderDto> queryAll();

    /**
     * 商家查询所有为发货订单
     * @param userId
     * @param orderStatus
     * @return
     */
    List<orderDto> getOneStatusOrders(Long userId,Integer orderStatus);

}
