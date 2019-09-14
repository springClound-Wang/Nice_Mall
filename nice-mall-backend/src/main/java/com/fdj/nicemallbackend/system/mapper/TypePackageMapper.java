package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.TypePackage;
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
public interface TypePackageMapper extends BaseMapper<TypePackage> {

    void save(TypePackage typePackage);

    TypePackage selectByGoodsId(Long goodsId);
}
