package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.dto.HotSort;
import com.fdj.nicemallbackend.system.entity.PopularSort;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-09-08
 */
@Mapper
public interface PopularSortMapper extends BaseMapper<PopularSort> {

    void save(PopularSort popularSort);

    List<HotSort> selectByImageMainId(@Param("imageMainId") Integer imageMainId);
}
