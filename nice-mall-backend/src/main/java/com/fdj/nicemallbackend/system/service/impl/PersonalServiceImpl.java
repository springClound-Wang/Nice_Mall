package com.fdj.nicemallbackend.system.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.ReceiptAddress;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.mapper.ReceiptAddressMapper;
import com.fdj.nicemallbackend.system.mapper.UserMapper;
import com.fdj.nicemallbackend.system.service.IPersonalService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname PersonalServiceImpl
 * @Description TODO
 * @Date 19-10-13 上午11:09
 * @Created by xns
 */
@Slf4j
@Service
public class PersonalServiceImpl implements IPersonalService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ReceiptAddressMapper receiptAddressMapper;

    /**
     * 修改 个人信息
     * @param user
     */
    @Override
    public void changeNew(User user,int flag) {
        User us = userMapper.selectByuserId(user.getUserId());
        if(flag==1&&!User.DEFAULT_AVATAR.equals(us.getUserAvatar())) {
            OssuploadUtil ossuploadUtil = new OssuploadUtil();
            if(!ossuploadUtil.deleteFile(us.getUserAvatar())){
                log.error("图片删除失败");
            }
        }
        userMapper.updateById(user);
    }

    @Override
    public Result getPersonal(Long userId) {
        User user = userMapper.selectByuserId(userId);
        return new Result().success(user,"查询成功");
    }

    /**
     * 添加收货地址
     * @param receiptAddress
     * @return
     */
    @Override
    public Result addAddress(ReceiptAddress receiptAddress) {
        if(receiptAddressMapper.save(receiptAddress)!=0){
            return new Result().success("添加成功!");
        }
        else{
            return new Result().fail("添加失败");
        }
    }

    /**
     * 获取添加的所有收货地址
     * @param userId
     * @return
     */
    @Override
    public Result getAllAddress(Long userId) {
        List<ReceiptAddress> addresses = receiptAddressMapper.selectByuserId(userId);
        if(addresses.isEmpty()){
            return new Result().fail(null,"暂时没有数据");
        }
        else{
            return new Result().success(addresses,"获取地址成功!");
        }
    }

    /**
     * 根据地址id删除地址
     * @param addressId
     * @return
     */
    @Override
    public Result deleteAddr(Long addressId) {
        if(receiptAddressMapper.deleteByaddressId(addressId)!=0){
            return new Result().success("删除成功!!");
        }else{
            return new Result().fail("删除失败!!");
        }
    }
}
