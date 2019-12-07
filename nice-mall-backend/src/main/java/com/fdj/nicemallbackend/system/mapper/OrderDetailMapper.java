package com.fdj.nicemallbackend.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdj.nicemallbackend.system.dto.orderDto;
import com.fdj.nicemallbackend.system.entity.OrderDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Classname OrderDetailMapper
 * @Description TODO
 * @Date 19-10-20 下午8:37
 * @Created by xns
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    void insertList(@Param("payData") List<OrderDetail> payData);

    /**
     * 商家查询某状态的订单
     * @param storeName
     * @param orderStatus
     * @return
     */
    List<orderDto> selectOneStatusOrder(@Param("storeName") String storeName,@Param("orderStatus") Integer orderStatus);

    List<orderDto> selectPartStatusOrder(String storeName);

    /**
     * 用户查询某一状态的订单
     * @param userId
     * @param orderStatus
     * @return
     */
    List<orderDto> OneStatusOrderByuserId(@Param("userId") Long userId,@Param("orderStatus") Integer orderStatus);

    /**
     * 用户查询所有订单
     * @param userId
     * @return
     */
    List<orderDto> allStatusOrderByuserId(Long userId);

    @Select("select * from m_order_detail where order_id = #{orderId} ")
    List<OrderDetail> selectByOrderId(String orderId);

    @Delete("delete from m_order_detail where order_id = #{orderId} ")
    void deleteByOrderId(String orderId);
}
