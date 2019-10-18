package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.authenication.Jwt_Get;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.ShopCart;
import com.fdj.nicemallbackend.system.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ShoppingCartController
 * @Description TODO
 * @Date 19-10-17 下午4:21
 * @Created by xns
 */
@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    IShoppingCartService iShoppingCartService;

    @Autowired
    Jwt_Get jwt_get;


    /**
     * 加入购物车
     * goodsColor,goodsId,goodsNum,goodsSize,userId
     * @param shopCart
     * @return
     */
    @PostMapping("/join")
    public Result joinCart(@RequestBody ShopCart shopCart, HttpServletRequest request){
        Long userId = jwt_get.getUser(request);
        shopCart.setUserId(userId);
        Result result = iShoppingCartService.addCart(shopCart);
        return result;
    }

    /**
     * 获取购物车中所有商品
     * @param request
     * @return
     */
    @GetMapping("gain")
    public Result getAllCart(HttpServletRequest request){
        Long userId = jwt_get.getUser(request);
        Result result = iShoppingCartService.getAllCart(userId);
        return result;
    }
}
