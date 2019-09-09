package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.SortImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-09-08
 */
@Mapper
public interface SortImageMapper extends BaseMapper<SortImage> {

    void save(SortImage sortImage);

    SortImage selectByUploadType(@Param("type") String type);
}
