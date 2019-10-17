package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.utils.JsonUtils;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.*;
import com.fdj.nicemallbackend.system.mapper.BusinessMapper;
import com.fdj.nicemallbackend.system.mapper.GoodsMapper;
import com.fdj.nicemallbackend.system.mapper.UserMapper;
import com.fdj.nicemallbackend.system.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Classname ShoppingCartServiceImpl
 * @Description TODO
 * @Date 19-10-17 下午4:30
 * @Created by xns
 */
@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    BusinessMapper businessMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    static final String KEY_PREFIX = "nice.cart.uid:";

    /**
     * 添加购物车
     * @return
     */
    @Override
    public Result addCart(ShopCart shopCart) {
        Long goodsId = shopCart.getGoodsId();
        Integer num = shopCart.getGoodsNum();
        User user = userMapper.selectByuserId(shopCart.getUserId());
        String key = KEY_PREFIX+user.getUserId();
        //判断redis中是否已经存在该商品
        BoundHashOperations<String,Object,Object> cartMap = redisTemplate.boundHashOps(key);
        Boolean haskey = cartMap.hasKey(goodsId.toString());
        //存在
        if(haskey){
            String shoppingcart = cartMap.get(goodsId.toString()).toString();
            shopCart = JsonUtils.parse(shoppingcart,ShopCart.class);
            shopCart.setGoodsNum(shopCart.getGoodsNum()+num);
        }
        else{
            //补全购物车信息
            Goods goods = goodsMapper.selectAllById(shopCart.getGoodsId());
            Business business = businessMapper.selectByGoodsId(shopCart.getGoodsId());
            shopCart.setImageMain(goods.getImageMain());
            shopCart.setGoodsName(goods.getGoodsName());
            shopCart.setStoreName(business.getStoreName());
            shopCart.setTotalPrice(shopCart.getGoodsPrice().multiply(BigDecimal.valueOf(shopCart.getGoodsNum())));
        }
        //存入
        cartMap.put(goodsId.toString(),JsonUtils.serialize(shopCart));
        return new Result().success("添加成功!!");
    }
}
