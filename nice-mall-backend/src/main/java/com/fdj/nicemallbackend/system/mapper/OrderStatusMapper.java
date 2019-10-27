package com.fdj.nicemallbackend.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdj.nicemallbackend.system.entity.OrderStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname OrderStatusMapper
 * @Description TODO
 * @Date 19-10-20 下午8:38
 * @Created by xns
 */
@Mapper
public interface OrderStatusMapper extends BaseMapper<OrderStatus> {
    /**
     * 保存订单状态
     * @param orderStatuss
     */
    @Insert("insert into m_order_status values(#{orderId} ,#{orderStatus} ,#{createTime},NULL,NULL,NULL)")
    void save(OrderStatus orderStatuss);

    /**
     * 更新订单状态
     * @param orderStatus1
     * @return
     */
    Integer updateByOrderId(OrderStatus orderStatus1);

    /**
     * 批量更新
     * @param orderIds
     * @param orderStatus
     * @return
     */
    Integer updateByOrderIdList(@Param("orderIds") List<String> orderIds, @Param("orderStatus")Integer orderStatus);

    @Select("select * from m_order_status where order_id = #{orderId} ")
    OrderStatus selectByOrderId(String orderId);
}
