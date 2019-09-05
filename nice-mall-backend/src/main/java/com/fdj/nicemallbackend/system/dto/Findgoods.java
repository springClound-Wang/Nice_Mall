package com.fdj.nicemallbackend.system.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname Findgoods
 * @Description TODO
 * @Date 19-9-5 下午9:37
 * @Created by xns
 */
@Data
public class Findgoods {
    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品主图片
     */
    private String imageMain;

    /**
     * 商品以前的价格
     */
    private BigDecimal goodsPrePrice;

    /**
     * 商品的现价
     */
    private BigDecimal goodsCurPrice;
}
