package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
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
@TableName("m_order_status")
public class OrderStatus extends Model<OrderStatus> {

    private static final long serialVersionUID=1L;

    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 订单状态 0: 未付款 1:已支付,未发货 2:已发货，待确认 ３：确认，待评价　４：已评价
     */
    private Integer orderStatus;

    /**
     * 下单时间
     */
    private LocalDateTime createTime;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;

    /**
     * 交易完成时间
     */
    private LocalDateTime endTime;

    public OrderStatus(){

    }

    public OrderStatus(String orderId, Integer orderStatus, LocalDateTime createTime) {
        this.orderId=orderId;
        this.orderStatus=orderStatus;
        this.createTime = createTime;
    }
}
