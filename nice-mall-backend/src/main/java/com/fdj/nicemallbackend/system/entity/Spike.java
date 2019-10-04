package com.fdj.nicemallbackend.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-10-03
 */
@Data
@TableName("m_spike")
public class Spike extends Model<Spike> {

    private static final long serialVersionUID=1L;

    /**
     * 秒杀id
     */
    @TableId(value = "spike_id", type = IdType.AUTO)
    private Long spikeId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品对应分类的id
     */
    private Integer goodsTypeId;

    /**
     * 商家id
     */
    private Long businessId;

    /**
     * 原价格
     */
    private BigDecimal prePrice;

    /**
     * 秒杀价格
     */
    private BigDecimal spikePrice;

    /**
     * 秒杀商品库存
     */
    private Long spikeNumber;

    /**
     * 秒杀商品剩余库存数
     */
    private Long spikeStockNumber;

    /**
     * 秒杀开始时间
     */
    private LocalDateTime startTime;

    /**
     * 秒杀结束时间
     */
    private LocalDateTime endTime;

    /**
     * 0:已结束 1：未开始
     */
    private Integer spikeState;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    @Override
    protected Serializable pkVal() {
        return this.spikeId;
    }

    public Spike(Long goodsId,Integer goodsTypeId,Long businessId,BigDecimal prePrice,BigDecimal spikePrice,Long spikeNumber,Long spikeStockNumber,LocalDateTime startTime,LocalDateTime endTime){
        this.goodsId=goodsId;
        this.goodsTypeId=goodsTypeId;
        this.businessId=businessId;
        this.prePrice=prePrice;
        this.spikePrice=spikePrice;
        this.spikeNumber=spikeNumber;
        this.spikeStockNumber=spikeStockNumber;
        this.startTime=startTime;
        this.endTime=endTime;
    }
}
