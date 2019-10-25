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
     * 更新订单状态
     * @param orderId
     * @param orderStatus
     * @return
     */
    String updateOrderStatus(String orderId, Integer orderStatus);


    /**
     * 商家查询所有为发货订单
     * @param userId
     * @param orderStatus
     * @return
     */
    List<orderDto> getOneStatusOrders(Long userId,Integer orderStatus);

    String updateListOrderStatus(List<String> orderId, Integer orderStatus);

    List<orderDto> getAllStatusOrders(Long userId);

    /**
     * 用户查询某一状态的订单
     * @return
     */
    List<orderDto> queryOneStatus(Long userId,Integer orderStatus);


    /**
     * 用户查询所有订单
     * @param userId
     * @return
     */
    List<orderDto> queryAllStatus(Long userId);

}
