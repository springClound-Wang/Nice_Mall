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
public class TypeShoes extends Model<TypeShoes> {

    private static final long serialVersionUID=1L;

    /**
     * 商品id
     */
    @TableId(value = "shoes_id", type = IdType.AUTO)
    private Long shoesId;

    /**
     * 对应商品表的id
     */
    private Long goodsId;

    /**
     * 鞋子尺码
     */
    private String shoesSize;

    /**
     * 鞋子颜色
     */
    private String shoesColor;

    /**
     * 鞋子适合的季节
     */
    private String shoesSeason;

    /**
     * 适用场合
     */
    private String shoesPlace;

    /**
     * 材质
     */
    private String shoesMaterial;

    /**
     * 商品展示图片
     */
    private String imageShow;

    /**
     * 商品细节图片
     */
    private String imageDetail;

    public TypeShoes(Long goodsId, String shoesSize, String shoesColor, String shoesSeason, String shoesPlace, String shoesMaterial, String imageShow, String imageDetail) {
        this.goodsId = goodsId;
        this.shoesSize = shoesSize;
        this.shoesColor = shoesColor;
        this.shoesSeason = shoesSeason;
        this.shoesPlace = shoesPlace;
        this.shoesMaterial = shoesMaterial;
        this.imageShow = imageShow;
        this.imageDetail = imageDetail;
    }
}
