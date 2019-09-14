package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.TypeShoes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-08-31
 */
@Mapper
public interface TypeShoesMapper extends BaseMapper<TypeShoes> {

    void save(TypeShoes typeShoes);

    TypeShoes selectByGoodsId(Long goodsId);
}
