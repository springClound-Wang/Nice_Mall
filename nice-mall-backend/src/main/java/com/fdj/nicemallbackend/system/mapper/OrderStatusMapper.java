package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.OrderStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
}
