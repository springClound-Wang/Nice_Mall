package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.OrderStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname OrderStatusMapper
 * @Description TODO
 * @Date 19-10-20 下午8:38
 * @Created by xns
 */
@Mapper
public interface OrderStatusMapper {
    /**
     * 保存订单状态
     * @param orderStatuss
     */
    @Insert("insert into m_order_status values(#{orderId} ,#{orderStatus} ,#{createTime},NULL,NULL,NULL)")
    void save(OrderStatus orderStatuss);
}
