package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.Business;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-08-22
 */
@Mapper
public interface BusinessMapper extends BaseMapper<Business> {

    /**
     * 通过userid查询
     *
     * @param userId
     * @return
     */
    Business selectByuserId(@Param("userId") Long userId);

    /**
     * 通过店名查询
     * @param storeName
     * @return
     */
    Business selectByStoreName(@Param("storeName") String storeName);

    /**
     * 通过身份证查询
     * @param businessIdentityNumber
     * @return
     */
    Business selectByIdentityNumber(String businessIdentityNumber);

    /**
     * 通过电话号码查询
     * @param businessPhone
     * @return
     */
    Business selectByPhone(String businessPhone);

    /**
     * 通过地址查询
     * @param businessAddress
     * @return
     */
    Business selectByAddress(String businessAddress);

    /**
     * 插入店家
     * @param business
     * @return
     */
    void insertShop(Business business);

    Business selectByBussinessId(Long businessId);

    Business selectByGoodsId(Long goodsId);
}
