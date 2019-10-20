package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-10-20
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void save(Order order);
}
