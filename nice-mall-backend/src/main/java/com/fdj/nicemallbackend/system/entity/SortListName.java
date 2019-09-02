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
 * @since 2019-09-01
 */
@Data
public class SortListName extends Model<SortListName> {

    private static final long serialVersionUID=1L;

    /**
     * 二阶分类的id
     */
    @TableId(value = "sort_list_id", type = IdType.AUTO)
    private Integer sortListId;

    /**
     * 一阶分类的id
     */
    private Integer sortId;

    /**
     * 二阶分类的名称
     */
    private String sortListName;

    public SortListName(Integer sortId, String sortListName) {
        this.sortId=sortId;
        this.sortListName=sortListName;
    }
}
