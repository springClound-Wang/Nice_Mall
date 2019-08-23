package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-08-16
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询角色
     *
     * @return
     */
    String selectByroleId(Integer uid);

}
