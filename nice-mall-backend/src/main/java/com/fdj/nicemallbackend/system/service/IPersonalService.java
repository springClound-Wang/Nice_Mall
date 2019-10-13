package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.User;

/**
 * @Classname IPersonalService
 * @Description TODO
 * @Date 19-10-13 上午11:08
 * @Created by xns
 */
public interface IPersonalService {
    /**
     * 修改个人信息
     * @param user
     */
    void changeNew(User user);

    /**
     * 获取个人信息
     * @param userId
     * @return
     */
    Result getPersonal(Long userId);
}
