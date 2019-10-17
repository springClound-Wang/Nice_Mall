package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Spikes;
import com.fdj.nicemallbackend.system.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdj.nicemallbackend.system.entity.ShopCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-08-31
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    void save(Goods goods);

    List<Findgoods> selectFuzzyByfiled(@Param("field") String field);

   Findgoods selectById(Long goodsId);

    Findgoods findById(Long goodsId);

    Goods selectAllById(Long goodsId);


    Spikes selectPart(Long goodsId);

    /**
     * 获取三条随机商品作为推荐
     * 跳过offset行，取limit条数据
     * @return
     */
    List<Findgoods> selectLimit(@Param("offset") int offset,@Param("limit") int limit);

    @Select("select count(*) from m_goods")
    Integer getCount();

}
