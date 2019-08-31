package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("m_store_goods")
public class StoreGoods extends Model<StoreGoods> {

    private static final long serialVersionUID=1L;

    /**
     * 商家id
     */
    private Long businessId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品库存量
     */
    private Long storeGoodsNumber;

    /**
     * 商品销售数量
     */
    private Long goodsSalesVolume;

    public StoreGoods(Long businessId, Long goodsId, Long storeGoodsNumber,Long goodsSalesVolume) {
        this.businessId = businessId;
        this.goodsId = goodsId;
        this.storeGoodsNumber = storeGoodsNumber;
        this.goodsSalesVolume = goodsSalesVolume;
    }
}
