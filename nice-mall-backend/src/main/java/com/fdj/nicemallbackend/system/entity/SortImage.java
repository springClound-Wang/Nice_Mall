package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-09-08
 */
@Data
@TableName("m_sort_image")
public class SortImage extends Model<SortImage> {

    private static final long serialVersionUID=1L;

    @TableId(value = "image_main_id", type = IdType.AUTO)
    private Integer imageMainId;

    /**
     * 大类型
     */
    private String uploadType;

    /**
     * 大图片
     */
    private String imageMains;


    public SortImage(String uploadType, String imageMains) {
        this.uploadType = uploadType;
        this.imageMains = imageMains;
    }
}
