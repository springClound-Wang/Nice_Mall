package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-09-08
 */
@Data
@TableName("m_popular_sort")
public class PopularSort extends Model<PopularSort> {

    private static final long serialVersionUID=1L;

    @TableId(value = "popular_id", type = IdType.AUTO)
    private Integer popularId;

    /**
     * 主图id
     */
    private Integer imageMainId;

    /**
     * 热门对应的小类型名称
     */
    private String typeName;

    /**
     * 热门相应的十张图片 
     */
    private String imageDetails;

    public PopularSort(Integer imageMainId,String typename, String imageDetails) {
        this.imageMainId = imageMainId;
        this.typeName=typename;
        this.imageDetails = imageDetails;
    }
}
