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
public class TypeClothes extends Model<TypeClothes> {

    private static final long serialVersionUID=1L;

    /**
     * 衣服类型商品的id
     */
    @TableId(value = "clothes_id", type = IdType.AUTO)
    private Long clothesId;

    /**
     * 对应商品表中的id
     */
    private Long goodsId;

    /**
     * 衣服尺码
     */
    private String clothesSize;

    /**
     * 衣服颜色
     */
    private String clothesColor;

    /**
     * 衣服适合的季节
     */
    private String clothesSeason;

    /**
     * 适合的人群
     */
    private String clothesPerson;

    /**
     * 商品展示图片
     */
    private String imageShow;

    /**
     * 商品细节图片
     */
    private String imageDetail;

    public TypeClothes(Long goodsId, String clothesSize,String clothesColor, String clothesSeason, String clothesPerson, String imageShow, String imageDetail) {
        this.goodsId = goodsId;
        this.clothesSize = clothesSize;
        this.clothesColor=clothesColor;
        this.clothesSeason = clothesSeason;
        this.clothesPerson = clothesPerson;
        this.imageShow = imageShow;
        this.imageDetail = imageDetail;
    }
}
