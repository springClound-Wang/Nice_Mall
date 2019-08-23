package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.Business;
import com.fdj.nicemallbackend.system.mapper.BusinessMapper;
import com.fdj.nicemallbackend.system.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        if(businessMapper.selectByuserId(userId)!=null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Result registerShop(Business business){
        Map<String,String> map = new HashMap<>();
        if(businessMapper.selectByuserId(business.getUserId())!=null){
            map.put("IdError","此账户已经被注册!!!");
        }
        if(businessMapper.selectByStoreName(business.getStoreName())!=null){
            map.put("storeNameError","店铺名称已经注册，请重新选择");
        }
        if(businessMapper.selectByIdentityNumber(business.getBusinessIdentityNumber())!=null){
            map.put("IdentityError","您的身份证号已经注册过!!!");
        }
        if(businessMapper.selectByPhone(business.getBusinessPhone())!=null){
            map.put("PhoneError","联系电话已经被使用!!");
        }
        if(businessMapper.selectByAddress(business.getBusinessAddress())!=null){
            map.put("AddressError","店铺地址已有对应店铺,请慎重");
        }
        if(map.isEmpty()){
            businessMapper.insertShop(business);
            return new Result().success("店铺创建成功!!!");
        }
        else{
            return new Result().fail(map,"店铺创建失败!!!");
        }
    }
}
