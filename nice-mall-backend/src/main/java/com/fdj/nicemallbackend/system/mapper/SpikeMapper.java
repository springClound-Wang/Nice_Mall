package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.Spike;
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
 * @since 2019-10-03
 */
@Mapper
public interface SpikeMapper extends BaseMapper<Spike> {

    void save(Spike spike);

//    @Select("select * from m_spike where goods_id = #{goodsId}")
    List<Spike> selectByGoodsId(Long goodsId);

}
