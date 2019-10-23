package com.fdj.nicemallbackend.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-10-20
 */
@Data
@TableName("m_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID=1L;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 下单用户id
     */
    private Long userId;

    /**
     * 下单用户昵称
     */
    private String userName;

    /**
     * 总金额
     */
    private BigDecimal totalMoney;

    /**
     * 物流名称
     */
    private String logisticsName;

    /**
     * 物流单号
     */
    private String shipmentNumber;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 收货人姓名
     */
    private String receiptName;

    /**
     * 收货人手机号
     */
    private String receiptPhone;

    /**
     * 收货地址
     */
    private String receiptAddress;

    @Transient
    private List<OrderDetail> payData;

    @Transient
    private Integer orderStatus;
}
