package com.fdj.nicemallbackend.system.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Classname orderDto
 * @Description TODO
 * @Date 19-10-23 下午3:11
 * @Created by xns
 */
@Data
public class orderDto {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品颜色
     */
    private String goodsColor;

    /**
     * 商品尺码
     */
    private String goodsSize;

    /**
     * 商品数量
     */
    private Integer goodsNum;

    /**
     * 商品单价
     */
    private BigDecimal goodsPrice;

    /**
     * 商品实付
     */
    private BigDecimal totalPrice;

    /**
     * 图片
     */
    private String imageMain;

    /**
     * 订单状态
     */
    private Integer orderStatus;

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

    /**
     * 前端需要的一个标志状态的标志
     */
    private boolean isShip;
}
