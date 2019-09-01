package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-09-01
 */
@TableName("m_type_goods")
public class TypeGoods extends Model<TypeGoods> {

    private static final long serialVersionUID=1L;

    /**
     * 商品三阶分类id
     */
    private Integer sortListTypeId;

    /**
     * 商品id
     */
    private Long goodsId;


    public Integer getSortListTypeId() {
        return sortListTypeId;
    }

    public void setSortListTypeId(Integer sortListTypeId) {
        this.sortListTypeId = sortListTypeId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TypeGoods{" +
        "sortListTypeId=" + sortListTypeId +
        ", goodsId=" + goodsId +
        "}";
    }
}
