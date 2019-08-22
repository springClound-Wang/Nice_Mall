package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.entity.Business;
import com.fdj.nicemallbackend.system.mapper.BusinessMapper;
import com.fdj.nicemallbackend.system.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-08-22
 */
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements IBusinessService {

    @Autowired
    BusinessMapper businessMapper;

    /**
     * 查看某用户是否拥有商铺
     * @param userId
     * @return
     */
    @Override
    public boolean isshop(Long userId) {
        if(businessMapper.selectByuserrId(userId)!=null){
            return true;
        }
        else{
            return false;
        }
    }
}
