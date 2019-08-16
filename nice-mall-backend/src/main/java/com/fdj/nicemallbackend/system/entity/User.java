package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author xns
 * @since 2019-08-16
 */
@Data
@TableName("m_user")
public class User extends Model<User> {

    public static final String SEX_UNKNOW = "保密";
    public static final String DEFAULT_AVATAR = "default.jpg";
    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String userPassword;

    private String userSex;

    private String userTelephone;

    private String userAvatar;

    private Date createTime;

    private Date modifyTime;
}
