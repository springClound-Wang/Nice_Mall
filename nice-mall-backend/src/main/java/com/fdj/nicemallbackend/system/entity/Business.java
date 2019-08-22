package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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


    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBusinessTrueName() {
        return businessTrueName;
    }

    public void setBusinessTrueName(String businessTrueName) {
        this.businessTrueName = businessTrueName;
    }

    public String getBusinessIdentityNumber() {
        return businessIdentityNumber;
    }

    public void setBusinessIdentityNumber(String businessIdentityNumber) {
        this.businessIdentityNumber = businessIdentityNumber;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.businessId;
    }

    @Override
    public String toString() {
        return "Business{" +
        "businessId=" + businessId +
        ", userId=" + userId +
        ", storeName=" + storeName +
        ", businessTrueName=" + businessTrueName +
        ", businessIdentityNumber=" + businessIdentityNumber +
        ", businessPhone=" + businessPhone +
        ", businessAddress=" + businessAddress +
        ", createTime=" + createTime +
        "}";
    }
}
