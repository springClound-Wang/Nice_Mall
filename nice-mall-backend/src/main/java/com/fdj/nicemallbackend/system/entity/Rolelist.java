package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-08-17
 */
@Data
@TableName("m_rolelist")
public class Rolelist extends Model<Rolelist> {

    private static final long serialVersionUID=1L;

    private Long userId;

    private Integer roleId;
}
