package com.fdj.nicemallbackend.system.controller;


import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.Order;
import com.fdj.nicemallbackend.system.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
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
     * @param order
     * @return
     */
    @PostMapping("/create")
    public Result  createOrder(@RequestBody Order order){
        Result result = iOrderService.createOrder(order);
        return result;
    }
}

