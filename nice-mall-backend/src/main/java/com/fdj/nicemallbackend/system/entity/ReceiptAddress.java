package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-10-14
 */
@Data
@TableName("m_receipt_address")
public class ReceiptAddress extends Model<ReceiptAddress> {

    private static final long serialVersionUID=1L;

    /**
     * 地址id
     */
    @TableId(value = "address_id", type = IdType.AUTO)
    private Long addressId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 收货人的姓名
     */
    private String receiptName;

    /**
     * 所在地区
     */
    private String receiptArea;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 收货手机号
     */
    private String receiptPhone;

    /**
     * 备用手机号
     */
    private String sparePhone;

    /**
     * 收货时间
     */
    private String receiptTime;

    /**
     * 是否为默认地址0:非默认，1：默认
     */
    private Integer isDefault;
}
