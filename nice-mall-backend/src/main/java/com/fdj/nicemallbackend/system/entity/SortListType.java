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
public class SortListType extends Model<SortListType> {

    private static final long serialVersionUID=1L;

    /**
     * 三阶分类的id
     */
    @TableId(value = "sort_list_type_id", type = IdType.AUTO)
    private Integer sortListTypeId;

    /**
     * 二阶分类的id
     */
    private Integer sortListId;

    /**
     * 三阶分类的名称
     */
    private String sortListName;

    public SortListType(Integer sortListId, String sortListName) {
        this.sortListId = sortListId;
        this.sortListName = sortListName;
    }
}
