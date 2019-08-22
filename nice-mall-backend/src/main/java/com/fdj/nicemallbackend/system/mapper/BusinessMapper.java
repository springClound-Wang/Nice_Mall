package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-08-22
 */
@Mapper
public interface BusinessMapper extends BaseMapper<Business> {

    /**
     * 通过userid查询
     *
     * @param userId
     * @return
     */
    Business selectByuserrId(@Param("userId") Long userId);
}
