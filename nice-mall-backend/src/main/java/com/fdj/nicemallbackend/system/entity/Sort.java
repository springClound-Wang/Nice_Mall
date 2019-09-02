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
 * @since 2019-09-02
 */
@Data
@TableName("m_sort")
public class Sort extends Model<Sort> {

    private static final long serialVersionUID=1L;

    /**
     * 一阶分类的id
     */
    @TableId(value = "sort_id", type = IdType.AUTO)
    private Integer sortId;

    /**
     * 一阶分类的名称
     */
    private String sortName;

    /**
     * 英文名称
     */
    private String sortEnglishName;

    /**
     * 有无数据
     */
    private Integer sortHave;

}
