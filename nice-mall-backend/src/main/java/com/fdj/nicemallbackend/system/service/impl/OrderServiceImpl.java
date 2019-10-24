package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.utils.IdWorker;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.StorageUpdate;
import com.fdj.nicemallbackend.system.dto.orderDto;
import com.fdj.nicemallbackend.system.entity.*;
import com.fdj.nicemallbackend.system.mapper.*;
import com.fdj.nicemallbackend.system.service.IBusinessService;
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

    @Autowired
    BusinessMapper businessMapper;

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
        String orderId = String.valueOf(idWorker.nextId());
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
            return new Result().success(String.valueOf(orderId), "下单成功");
        }
        else{
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new Result().fail("库存不足，无法下单");
        }
    }

    /**
     * 设置要更新的状态
     * @param orderId
     * @param orderStatus
     * @return
     */
    public OrderStatus setStatus(String orderId,Integer orderStatus){
        OrderStatus orderStatus1 = new OrderStatus();
        orderStatus1.setOrderId(orderId);
        LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        switch (orderStatus) {
            case 1:
                orderStatus1.setPaymentTime(localDateTime);
                break;
            case 2:
                orderStatus1.setDeliveryTime(localDateTime);
                break;
            case 3:
                orderStatus1.setEndTime(localDateTime);
                break;
            default:
                log.error("状态不存在!");
                break;
        }
        orderStatus1.setOrderStatus(orderStatus);
        return orderStatus1;
    }

    /**
     * 更新订单状态
     * @param orderId
     * @param orderStatus
     * @return
     */
    @Override
    public String updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus orderStatus1 = setStatus(orderId,orderStatus);
        if(orderStatusMapper.updateByOrderId(orderStatus1)>0){
            return orderId;
        }else{
            log.error("更新出错");
            return null;
        }
    }

    /**
     * 查询所有订单
     * @return
     */
    @Override
    public List<orderDto> queryAll() {
        return null;
    }

    /**
     * 商家查询订单
     * @return
     */
    @Override
    public List<orderDto> getOneStatusOrders(Long userId,Integer orderStatus){
        List<orderDto> lists = new ArrayList<>();
        Business business = businessMapper.selectByuserId(userId);
        if(business == null){
            return lists;
        }
        lists = orderDetailMapper.selectOneStatusOrder(business.getStoreName(),orderStatus);
        return lists;
    }

    /**
     * 批量更新状态
     * @param orderId
     * @param orderStatus
     * @return
     */
    @Override
    public String updateListOrderStatus(List<String> orderId,Integer orderStatus) {
        if (orderStatusMapper.updateByOrderIdList(orderId, orderStatus) > 0) {
            return "操作成功";
        } else {
            log.error("批量更新出错");
            return null;
        }
    }

    /**
     * 商家显示发货和未发货订单
     * @param userId
     * @return
     */
    @Override
    public List<orderDto> getAllStatusOrders(Long userId) {
        List<orderDto> lists = new ArrayList<>();
        Business business = businessMapper.selectByuserId(userId);
        if(business == null){
            return lists;
        }
        lists = orderDetailMapper.selectPartStatusOrder(business.getStoreName());
        for(int i=0;i<lists.size();i++){
            switch (lists.get(i).getOrderStatus()){
                case 1:
                    lists.get(i).setShip(false);
                    break;
                case 2:
                    lists.get(i).setShip(true);
                    break;
                default:
                    log.error("状态有误，不能识别");
                    break;
            }
        }
        return lists;
    }
}

