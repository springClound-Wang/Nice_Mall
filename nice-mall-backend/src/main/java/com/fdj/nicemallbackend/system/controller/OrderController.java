package com.fdj.nicemallbackend.system.controller;


import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.orderDto;
import com.fdj.nicemallbackend.system.entity.Order;
import com.fdj.nicemallbackend.system.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xns
 * @since 2019-10-20
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService iOrderService;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @PostMapping("/create")
    public Result createOrder(@RequestBody Order order) {
        Result result = iOrderService.createOrder(order);
        return result;
    }

    /**
     * 支付
     * @param map
     * @return
     */
    @PostMapping("/pay")
    public Result Payment(@RequestBody Map<String,String> map){
        Result result = new Result();
        Integer orderStatus = Integer.parseInt(map.get("orderStatus"));
        if(orderStatus == 0){
            return result.fail("请在30分钟内支付哦!否则订单会失效哟!");
        }
        if(orderStatus == 1){
            Long orderId = Long.valueOf(map.get("orderId"));
            result = iOrderService.updateOrderStatus(orderId,orderStatus);
        }
        return result;
    }

     /**
     * 查询订单
     * @param request
     * @return
     */
    @GetMapping("/query")
    public Result queryOrder(HttpServletRequest request) {
        List<orderDto> orders= iOrderService.queryAll();
        if(CollectionUtils.isEmpty(orders)){
            return new Result().fail("暂时无订单数据!!");
        }
        return new Result().success(orders,"查询成功!!!");
    }
}

