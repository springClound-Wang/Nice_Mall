package com.fdj.nicemallbackend.system.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname goodsList
 * @Description TODO
 * @Date 19-9-24 下午5:20
 * @Created by xns
 */
@Data
public class goodsList {

    /**
     * 所属大类型
     */
    private String goodsType;

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

    /**
     * 商品库存量
     */
    private Long storeGoodsNumber;

    /**
     * 商品品牌
     */
    private String goodsBrand;

    /**
     * 是否为推荐商品
     */
    private boolean goodsRecommend;

    /**
     * 是否为秒杀商品
     */
    private boolean goodsCrazy;


    public goodsList(String goodsType,Long goodsId, String goodsName, String imageMain,BigDecimal goodsPrePrice, BigDecimal goodsCurPrice, String goodsBrand, Long storeGoodsNumber,boolean goodsRecommend,boolean goodsCrazy) {
        this.goodsType=goodsType;
        this.goodsId=goodsId;
        this.goodsName=goodsName;
        this.imageMain=imageMain;
        this.goodsPrePrice=goodsPrePrice;
        this.goodsCurPrice=goodsCurPrice;
        this.goodsBrand=goodsBrand;
        this.storeGoodsNumber=storeGoodsNumber;
        this.goodsRecommend = goodsRecommend;
        this.goodsCrazy = goodsCrazy;
    }
}
