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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-10-20
 */
@Slf4j
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
        LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        order.setCreateTime(localDateTime);
        orderMapper.save(order);

        //保存订单状态
        OrderStatus orderStatus = new OrderStatus(orderId,0,order.getCreateTime());
        orderStatusMapper.save(orderStatus);


        List<StorageUpdate> lists = new ArrayList<>();
        for(OrderDetail orderDetail : order.getPayData()){
            orderDetail.setOrderId(orderId);
            StorageUpdate storageUpdate = new StorageUpdate();
            storageUpdate.setGoodsId(orderDetail.getGoodsId());
            storageUpdate.setGoodsNum(orderDetail.getGoodsNum());
            lists.add(storageUpdate);
        }
        //批量插入订单详情
        orderDetailMapper.insertList(order.getPayData());
        //更新库存
        int isjuge = storeGoodsMapper.decreaseStock(lists);
        if(isjuge>0) {
            return new Result().success(orderId, "下单成功");
        }
        else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new Result().fail("库存不足，无法下单");
        }
    }

    /**
     * 更新订单状态
     * @param orderId
     * @param orderStatus
     * @return
     */
    @Override
    public Result updateOrderStatus(Long orderId, Integer orderStatus) {
        OrderStatus orderStatus1 = new OrderStatus();
        orderStatus1.setOrderId(orderId);
        LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        switch (orderStatus) {
            case 1:
                orderStatus1.setPaymentTime(localDateTime);
                break;
            case 2:
                orderStatus1.setDeliveryTime(localDateTime);
            case 3:
                orderStatus1.setEndTime(localDateTime);
        }
        orderStatus1.setOrderStatus(1);
        if(orderStatusMapper.updateByOrderId(orderStatus1)<=0){
            log.error("支付失败，更新出错");
        }
        return new Result().success(orderId,"支付成功");
    }
}

