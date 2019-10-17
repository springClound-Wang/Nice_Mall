package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.ShopCart;

/**
 * @Classname IShoppingCartService
 * @Description TODO
 * @Date 19-10-17 下午4:29
 * @Created by xns
 */
public interface IShoppingCartService {
    /**
     * 加入购物车
     * @return
     */
    Result addCart(ShopCart shopCart);
}
