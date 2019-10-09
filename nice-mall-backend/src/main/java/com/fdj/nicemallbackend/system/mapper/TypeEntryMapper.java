package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.TypeEntry;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-09-18
 */
@Mapper
public interface TypeEntryMapper extends BaseMapper<TypeEntry> {

    @Select("select * from m_type_entry")
    List<TypeEntry> selectAll();
}
