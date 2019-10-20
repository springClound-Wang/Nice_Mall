package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname OrderDetailMapper
 * @Description TODO
 * @Date 19-10-20 下午8:37
 * @Created by xns
 */
@Mapper
public interface OrderDetailMapper {

    void insertList(@Param("payData") List<OrderDetail> payData);
}
