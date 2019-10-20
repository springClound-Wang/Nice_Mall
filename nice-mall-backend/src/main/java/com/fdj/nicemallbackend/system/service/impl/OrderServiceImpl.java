package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.utils.IdWorker;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.StorageUpdate;
import com.fdj.nicemallbackend.system.entity.Order;
import com.fdj.nicemallbackend.system.entity.OrderDetail;
import com.fdj.nicemallbackend.system.entity.OrderStatus;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.mapper.*;
import com.fdj.nicemallbackend.system.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-10-20
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderStatusMapper orderStatusMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    StoreGoodsMapper storeGoodsMapper;

    /**
     * 创建订单
     * @param order
     * @return
     */
    @Override
    @Transactional
    public Result createOrder(Order order) {
        IdWorker idWorker = new IdWorker();
        User user =userMapper.selectByuserId(order.getUserId());
        Long orderId = idWorker.nextId();
        order.setOrderId(orderId);
        order.setUserName(user.getUserName());
        orderMapper.save(order);

        //保存订单状态
        OrderStatus orderStatus = new OrderStatus(orderId,order.getOrderStatus(),order.getCreateTime());
        orderStatusMapper.save(orderStatus);


        List<StorageUpdate> lists = new ArrayList<>();
        StorageUpdate storageUpdate = new StorageUpdate();
        for(OrderDetail orderDetail : order.getPayData()){
            orderDetail.setOrderId(orderId);
            storageUpdate.setGoodsId(orderDetail.getGoodsId());
            storageUpdate.setGoodsNum(orderDetail.getGoodsNum());
            lists.add(storageUpdate);
        }
        //批量插入订单详情
        orderDetailMapper.insertList(order.getPayData());
        //更新库存
        storeGoodsMapper.decreaseStock(lists);
        return new Result().success("下单成功");
    }
}
