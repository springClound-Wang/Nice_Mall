package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.Sort;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-09-02
 */
@Mapper
public interface SortMapper extends BaseMapper<Sort> {

    Sort selectId(String name);
}
