package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.SortListName;
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
 * @since 2019-09-01
 */
@Mapper
public interface SortListNameMapper extends BaseMapper<SortListName> {

    SortListName selectId(String sortListName);

    void save(SortListName sortListName1);

    List<SortListName> select();

    List<SortListName> selectBysortId(Integer sortId);

    List<Integer> selectByPartName(@Param("partName") String partName,@Param("id") Integer id);

    List<Integer> selectByPartNames(@Param("partName") String partName,@Param("id") Integer id);

    List<Integer> selectByName(String sortListName);
}
