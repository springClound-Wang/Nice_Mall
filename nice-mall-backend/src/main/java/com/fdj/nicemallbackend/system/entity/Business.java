package com.fdj.nicemallbackend.system.entity;

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
 * @since 2019-08-22
 */
@Data
@TableName("m_business")
public class Business extends Model<Business> {

    private static final long serialVersionUID=1L;

    /**
     * 商家id
     */
    @TableId(value = "business_id", type = IdType.AUTO)
    private Long businessId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 店铺名称
     */
    private String storeName;

    /**
     * 商家真实姓名
     */
    private String businessTrueName;

    /**
     * 身份证号
     */
    private String businessIdentityNumber;

    /**
     * 商家联系电话
     */
    private String businessPhone;

    /**
     * 商家店铺地址
     */
    private String businessAddress;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
