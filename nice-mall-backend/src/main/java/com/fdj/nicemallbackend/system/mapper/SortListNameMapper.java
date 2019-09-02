package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.SortListName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-09-01
 */
@Mapper
public interface SortListNameMapper extends BaseMapper<SortListName> {

    SortListName selectId(String sortListName);

    SortListName save(SortListName sortListName1);
}
