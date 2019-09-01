package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-09-01
 */
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


    public Integer getSortListTypeId() {
        return sortListTypeId;
    }

    public void setSortListTypeId(Integer sortListTypeId) {
        this.sortListTypeId = sortListTypeId;
    }

    public Integer getSortListId() {
        return sortListId;
    }

    public void setSortListId(Integer sortListId) {
        this.sortListId = sortListId;
    }

    public String getSortListName() {
        return sortListName;
    }

    public void setSortListName(String sortListName) {
        this.sortListName = sortListName;
    }

    @Override
    protected Serializable pkVal() {
        return this.sortListTypeId;
    }

    @Override
    public String toString() {
        return "SortListType{" +
        "sortListTypeId=" + sortListTypeId +
        ", sortListId=" + sortListId +
        ", sortListName=" + sortListName +
        "}";
    }
}
