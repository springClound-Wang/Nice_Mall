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
public class TypePackage extends Model<TypePackage> {

    private static final long serialVersionUID=1L;

    /**
     * 商品id
     */
    @TableId(value = "package_id", type = IdType.AUTO)
    private Long packageId;

    /**
     * 对应商品表的id
     */
    private Long goodsId;

    /**
     * 包包大小
     */
    private String packageSize;

    /**
     * 包包颜色
     */
    private String packageColor;

    /**
     * 有无肩带
     */
    private String packageSash;

    /**
     * 包包适用的性别
     */
    private String packageSex;

    /**
     * 包包适合的季节
     */
    private String packageSeason;

    /**
     * 包包的重量
     */
    private String packageWeight;

    /**
     * 包包的材质
     */
    private String packageMaterial;

    /**
     * 商品展示图片
     */
    private String imageShow;

    /**
     * 商品细节图片
     */
    private String imageDetail;

    public TypePackage(Long goodsId, String packageSize, String packageColor, String packageSash, String packageSex, String packageSeason, String packageWeight, String packageMaterial, String imageShow, String imageDetail) {
        this.goodsId = goodsId;
        this.packageSize = packageSize;
        this.packageColor = packageColor;
        this.packageSash = packageSash;
        this.packageSex = packageSex;
        this.packageSeason = packageSeason;
        this.packageWeight = packageWeight;
        this.packageMaterial = packageMaterial;
        this.imageShow = imageShow;
        this.imageDetail = imageDetail;
    }
}
