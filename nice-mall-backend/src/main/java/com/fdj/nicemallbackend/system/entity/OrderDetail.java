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
 * @since 2019-10-20
 */
@Data
@TableName("m_order_detail")
public class OrderDetail extends Model<OrderDetail> {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "detail_id", type = IdType.AUTO)
    private Long detailId;

    /**
     * 关联订单表的id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 购买数量
     */
    private Integer goodsNum;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 颜色
     */
    private String goodsColor;

    /**
     * 尺码
     */
    private String goodsSize;

    /**
     * 主展示图
     */
    private String imageMain;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 总价
     */
    private BigDecimal totalPrice;
}
