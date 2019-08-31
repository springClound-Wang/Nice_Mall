package com.fdj.nicemallbackend.system.entity;

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
public class TypeElectronic extends Model<TypeElectronic> {

    private static final long serialVersionUID=1L;

    /**
     * 电子产品的id
     */
    @TableId(value = "electronic_id", type = IdType.AUTO)
    private Long electronicId;

    /**
     * 对应商品表的id
     */
    private Long goodsId;

    /**
     * 颜色
     */
    private String electronicColor;

    /**
     * 规格
     */
    private String electronicFormat;

    /**
     * 系统
     */
    private String electronicSystem;

    /**
     * 是否为双卡
     */
    private String electronicDoubleCard;

    /**
     * 分辨率
     */
    private String electronicResolution;

    /**
     * 前置像素
     */
    private String electronicFront;

    /**
     * 后置像素
     */
    private String electronicBackend;

    /**
     * 屏幕尺寸
     */
    private String electronicScreenSize;

    /**
     * 配件
     */
    private String phoneFitting;

    /**
     * 商品展示图片
     */
    private String imageShow;

    /**
     * 商品细节图片
     */
    private String imageDetail;

    public TypeElectronic(Long goodsId, String electronicColor, String electronicFormat, String electronicSystem, String electronicDoubleCard, String electronicResolution, String electronicFront, String electronicBackend, String electronicScreenSize, String phoneFitting, String imageShow, String imageDetail) {
        this.goodsId = goodsId;
        this.electronicColor = electronicColor;
        this.electronicFormat = electronicFormat;
        this.electronicSystem = electronicSystem;
        this.electronicDoubleCard = electronicDoubleCard;
        this.electronicResolution = electronicResolution;
        this.electronicFront = electronicFront;
        this.electronicBackend = electronicBackend;
        this.electronicScreenSize = electronicScreenSize;
        this.phoneFitting = phoneFitting;
        this.imageShow = imageShow;
        this.imageDetail = imageDetail;
    }
}
