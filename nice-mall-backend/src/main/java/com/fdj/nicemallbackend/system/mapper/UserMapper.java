package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-08-16
 */
@Mapper
public interface UserMapper {

    /**
     * 返回所有用户的姓名
     * @return
     */
    List<String> selectAllName();

    /**
     * 将用户信息插入数据库
     * @param user
     */
    void insertsave(User user);

    /**
     * 通过名称查询用户信息
     * @param username
     * @return
     */
    User findByName(@Param("username") String username);

    /**
     * By phone 查询
     * @param telephone
     * @return
     */
    User findByPhone(@Param("telephone") String telephone);

    /**
     * 查询所有用户信息
     * @return
     */
    List<User> selectAll();

    /**
     * 修改用户的信息
     * @param user
     */
    void updateById(User user);

    @Select("select * from m_user where user_id = #{userId} ")
    User selectByuserId(Long userId);
}
