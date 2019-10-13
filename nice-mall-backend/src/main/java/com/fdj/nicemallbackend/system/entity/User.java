package com.fdj.nicemallbackend.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;


/**
 *
 * @author xns
 * @since 2019-08-16
 */
@Data
@TableName("m_user")
public class User extends Model<User> {

    public static final String SEX_UNKNOW = "保密";
    public static final String DEFAULT_AVATAR = "http://nice-mall-oss.oss-cn-beijing.aliyuncs.com/mall/images/Avatar/1570964480364.jpeg";
    private static final long serialVersionUID=1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String userName;

    private String userTrueName;

    private String userPassword;

    private String userSex;

    private String userTelephone;

    private String userBirth;

    private String userAddress;

    private String userAvatar;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    public User(){
    }

    public User(Long userId, String userName, String userTrueName, String userAvatar, String userSex, String userBirth, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userTrueName = userTrueName;
        this.userAvatar=userAvatar;
        this.userSex=userSex;
        this.userBirth=userBirth;
        this.userAddress=userAddress;
    }
}
