package com.fdj.nicemallbackend.system.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        Boolean haskey = cartMap.hasKey(goodsId.toString()+shopCart.getGoodsColor()+shopCart.getGoodsSize());
        //存在
        if(haskey){
            String shoppingcart = cartMap.get(goodsId.toString()+shopCart.getGoodsColor()+shopCart.getGoodsSize()).toString();
            shopCart = JsonUtils.parse(shoppingcart,ShopCart.class);
            shopCart.setGoodsNum(shopCart.getGoodsNum() + num);
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
        cartMap.put(goodsId.toString()+shopCart.getGoodsColor()+shopCart.getGoodsSize(),JsonUtils.serialize(shopCart));
        return new Result().success("添加成功!!");
    }

    /**
     * 获取购物车中的所有商品
     * @param userId
     * @return
     */
    @Override
    public Result getAllCart(Long userId) {
        List<ShopCart> shopCarts = new ArrayList<>();
        String key = KEY_PREFIX+userId;
        if(redisTemplate.hasKey(key)){
            BoundHashOperations<String,Object,Object> cartMap = redisTemplate.boundHashOps(key);
            List<Object> cartStrs = cartMap.values();
            if(cartStrs.isEmpty()){
                return new Result().fail("未查到任何商品");
            }
            shopCarts = cartStrs.stream().map(shopCart->JsonUtils.parse(shopCart.toString(),ShopCart.class)).collect(Collectors.toList());
            return new Result().success(shopCarts,"查到了!");
        }else{
            return new Result().fail(null,"未查到，快去添加商品到购物车吧");
        }
    }

    /**
     * 删除购物车中指定的商品
     * @param userId
     * @param goodsColor
     * @param goodsSize
     * @param goodsId
     * @return
     */
    @Override
    public Result delOneCart(Long userId,String goodsColor,String goodsSize,Long goodsId) {
        String key = KEY_PREFIX+userId;
        if(redisTemplate.hasKey(key)) {
            BoundHashOperations<String, Object, Object> cartMap = redisTemplate.boundHashOps(key);
            if(cartMap.delete(goodsId.toString()+goodsColor+goodsSize)==1){
                return new Result().success("删除成功!");
            }
        }
        return new Result().fail("删除失败!");
    }

}
