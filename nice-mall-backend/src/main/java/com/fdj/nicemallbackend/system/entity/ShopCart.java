package com.fdj.nicemallbackend.system.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname ShopCart
 * @Description TODO
 * @Date 19-10-17 下午3:42
 * @Created by xns
 */
@Data
public class ShopCart {
    /**
     * 用户id
     */
    private Long UserId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 主图片
     */
    private String imageMain;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 购买数量
     */
    private Integer goodsNum;

    /**
     * 商品尺码
     */
    private String goodsSize;

    /**
     * 商品颜色
     */
    private String goodsColor;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品小计
     */
    private BigDecimal totalPrice;
}
