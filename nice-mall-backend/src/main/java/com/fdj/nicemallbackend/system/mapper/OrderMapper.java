package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @author xns
 * @since 2019-10-20
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    void save(Order order);

    @Select("select * from m_order where order_id = #{orderId} ")
    Order selectByOrderId(String orderId);
}
