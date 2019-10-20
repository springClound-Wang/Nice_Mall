package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.dto.StorageUpdate;
import com.fdj.nicemallbackend.system.entity.Goods;
import com.fdj.nicemallbackend.system.entity.StoreGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-08-31
 */
@Mapper
public interface StoreGoodsMapper extends BaseMapper<StoreGoods> {

    void save(StoreGoods storeGoods);

    StoreGoods selectBygoodsId(Long goodsId);

    List<StoreGoods> selectBybusinessId(Long id);

    void decreaseStock(@Param("storages") List<StorageUpdate> storages);
}
