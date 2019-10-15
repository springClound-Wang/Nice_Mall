package com.fdj.nicemallbackend.system.mapper;

import com.fdj.nicemallbackend.system.entity.ReceiptAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xns
 * @since 2019-10-14
 */
@Mapper
public interface ReceiptAddressMapper extends BaseMapper<ReceiptAddress> {

    int save(ReceiptAddress receiptAddress);
}
