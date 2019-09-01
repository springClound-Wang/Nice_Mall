package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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


    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    @Override
    protected Serializable pkVal() {
        return this.sortId;
    }

    @Override
    public String toString() {
        return "Sort{" +
        "sortId=" + sortId +
        ", sortName=" + sortName +
        "}";
    }
}
