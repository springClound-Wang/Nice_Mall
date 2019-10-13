package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.mapper.UserMapper;
import com.fdj.nicemallbackend.system.service.IPersonalService;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname PersonalServiceImpl
 * @Description TODO
 * @Date 19-10-13 上午11:09
 * @Created by xns
 */
@Service
public class PersonalServiceImpl implements IPersonalService {

    @Autowired
    UserMapper userMapper;

    /**
     * 修改 个人信息
     * @param user
     */
    @Override
    public void changeNew(User user) {
        userMapper.updateById(user);
    }

    @Override
    public Result getPersonal(Long userId) {
        User user = userMapper.selectByuserId(userId);
        return new Result().success(user,"查询成功");
    }
}
