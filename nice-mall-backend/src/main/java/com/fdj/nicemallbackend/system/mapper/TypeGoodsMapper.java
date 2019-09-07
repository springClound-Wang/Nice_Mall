package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.TypeGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-09-02
 */
@Mapper
public interface TypeGoodsMapper extends BaseMapper<TypeGoods> {

    void save(TypeGoods typeGoods);

    List<Long> selectBytypeId(Integer listTypeId);

    List<Long> selectByListNameId(Integer listNameId);

    List<Long> selectBySortId(Integer sortId);
}
