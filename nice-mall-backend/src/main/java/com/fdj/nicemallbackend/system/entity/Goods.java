package com.fdj.nicemallbackend.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-08-31
 */
@Data
@TableName("m_goods")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID=1L;

    /**
     * 商品id
     */
    @TableId(value = "goods_id", type = IdType.AUTO)
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品描述
     */
    private String goodsDesc;

    /**
     * 商品以前的价格
     */
    private BigDecimal goodsPrePrice;

    /**
     * 商品的现价
     */
    private BigDecimal goodsCurPrice;

    /**
     * 商品品牌
     */
    private String goodsBrand;

    /**
     * 商品的产地
     */
    private String goodsPlace;

    /**
     * 商品主图片
     */
    private String imageMain;

    public Goods( String goodsName, String goodsDesc, BigDecimal goodsPrePrice, BigDecimal goodsCurPrice, String goodsBrand, String goodsPlace, String imageMain) {
        this.goodsName = goodsName;
        this.goodsDesc = goodsDesc;
        this.goodsPrePrice = goodsPrePrice;
        this.goodsCurPrice = goodsCurPrice;
        this.goodsBrand = goodsBrand;
        this.goodsPlace = goodsPlace;
        this.imageMain = imageMain;
    }
}
