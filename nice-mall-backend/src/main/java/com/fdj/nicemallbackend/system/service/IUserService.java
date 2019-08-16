package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-08-15
 */
public interface IUserService{

    /**
     * 根据名称返回用户信息
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 验证并注册
     * @param telephone
     * @param password
     * @param code
     * @return
     */
    Boolean regist(String telephone, String password,String code);
}
