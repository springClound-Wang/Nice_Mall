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


    public Integer getSortListId() {
        return sortListId;
    }

    public void setSortListId(Integer sortListId) {
        this.sortListId = sortListId;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getSortListName() {
        return sortListName;
    }

    public void setSortListName(String sortListName) {
        this.sortListName = sortListName;
    }

    @Override
    protected Serializable pkVal() {
        return this.sortListId;
    }

    @Override
    public String toString() {
        return "SortListName{" +
        "sortListId=" + sortListId +
        ", sortId=" + sortId +
        ", sortListName=" + sortListName +
        "}";
    }
}
