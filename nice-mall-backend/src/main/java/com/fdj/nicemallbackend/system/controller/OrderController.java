package com.fdj.nicemallbackend.system.controller;


import com.fdj.nicemallbackend.common.authenication.Jwt_Get;
import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.orderDto;
import com.fdj.nicemallbackend.system.entity.Order;
import com.fdj.nicemallbackend.system.service.IMixService;
import com.fdj.nicemallbackend.system.service.IOrderService;
import org.apache.ibatis.annotations.Param;
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

    @Autowired
    IMixService iMixService;

    @Autowired
    Jwt_Get jwt_get;

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
     *
     * @param map
     * @return
     */
    @PostMapping("/pay")
    public Result Payment(@RequestBody Map<String, String> map) {
        String res = null;
        Integer orderStatus = Integer.parseInt(map.get("orderStatus"));
        if (orderStatus == 0) {
            return new Result().fail("请在30分钟内支付哦!否则订单会失效哟!");
        }
        if (orderStatus == 1) {
            String orderId = map.get("orderId");
            res = iOrderService.updateOrderStatus(orderId, orderStatus);
        }
        if (res == null) {
            return new Result().fail("支付失败!!");
        } else {
            return new Result().success(res, "支付成功!");
        }
    }


    /**
     * 用户确认收货
     * @param map
     * @return
     */
    @PutMapping("/confirm")
    public Result confirmReceipt(@RequestBody Map<String, Object> map) {
        Integer orderStatus = (Integer) map.get("orderStatus");
        String orderId = (String) map.get("orderId");
        String res = iOrderService.updateOrderStatus(orderId, orderStatus);
        if (res == null) {
            return new Result().fail("确认收货失败!");
        } else {
            return new Result().success(res, "确认收货成功!");
        }
    }


    /**
     * 用户查询某一状态订单
     *
     * @param request
     * @return
     */
    @GetMapping("/query")
    public Result queryOrder(HttpServletRequest request, @RequestParam Integer orderStatus) {
        Long userId = jwt_get.getUser(request);
        List<orderDto> orders = iOrderService.queryOneStatus(userId, orderStatus);
        if (CollectionUtils.isEmpty(orders)) {
            return new Result().fail(null, "暂时无订单数据!!");
        }
        return new Result().success(orders, "查询成功!!!");
    }

    /**
     * 用户查询所有订单
     *
     * @param request
     * @return
     */
    @GetMapping("/queryall")
    public Result queryOrder(HttpServletRequest request) {
        Long userId = jwt_get.getUser(request);
        List<orderDto> orders = iOrderService.queryAllStatus(userId);
        if (CollectionUtils.isEmpty(orders)) {
            return new Result().fail(null, "暂时无订单数据!!");
        }
        return new Result().success(orders, "查询成功!!!");
    }

    /**
     * 获取不定数目的推荐商品
     *
     * @return
     */
    @GetMapping("/recommend")
    public Result getRecommend() {
        List<Findgoods> lists = iMixService.getFreeRecommend();
        if (CollectionUtils.isEmpty(lists)) {
            return new Result().fail(null, "暂时没有推荐商品");
        } else {
            return new Result().success(lists, "查询成功!!!");
        }
    }

    /**
     * 用户获取订单详情
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public Result getOrderDetail(@RequestParam String orderId){
        Order order = iOrderService.getOrderDetail(orderId);
        if(order!=null){
            return new Result().success(order,"获取成功");
        }
        else{
            return new Result().fail(null,"订单详情获取失败");
        }
    }

}

