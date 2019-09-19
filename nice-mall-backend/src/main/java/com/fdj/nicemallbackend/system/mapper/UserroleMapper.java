package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.Rolelist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-08-17
 */
@Mapper
public interface UserroleMapper extends BaseMapper<Rolelist> {

    /**
     * 查询某一个用户的所有角色
     * @param userId
     * @return
     */
    List<Integer> selectRolesByuid(Long userId);

    /**
     * 保存角色和用户的关系
     * @param roleuser
     */
    void save(Rolelist roleuser);

}
