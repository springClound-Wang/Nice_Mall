package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-09-02
 */
@Data
@TableName("m_type_goods")
public class TypeGoods extends Model<TypeGoods> {

    private static final long serialVersionUID=1L;

    /**
     * 商品一阶分类id
     */
    private Integer sortId;

    /**
     * 商品二阶分类id
     */
    private Integer sortListNameId;

    /**
     * 商品三阶分类id
     */
    private Integer sortListTypeId;

    /**
     * 商品id
     */
    private Long goodsId;

    public TypeGoods(Integer sortId, Integer sortListNameId, Integer sortListTypeId, Long goodsId) {
        this.sortId = sortId;
        this.sortListNameId = sortListNameId;
        this.sortListTypeId = sortListTypeId;
        this.goodsId = goodsId;
    }
}
