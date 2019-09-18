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
 * @since 2019-09-18
 */
@Data
@TableName("m_type_entry")
public class TypeEntry extends Model<TypeEntry> {

    private static final long serialVersionUID = 1L;

    /**
     * 类型入口id
     */
    @TableId(value = "entry_id", type = IdType.AUTO)
    private Integer entryId;

    /**
     * 类型
     */
    private String entryName;

    /**
     * 标题
     */
    private String entryTitle;

    /**
     * 图片
     */
    private String entryImage;

    /**
     * 折扣
     */
    private Float entryDiscount;

    public TypeEntry(String entryName, String entryTitle, String url, Float entryDiscount) {
        this.entryName=this.entryName;
        this.entryTitle=entryTitle;
        this.entryImage=url;
        this.entryDiscount = entryDiscount;
    }
}
