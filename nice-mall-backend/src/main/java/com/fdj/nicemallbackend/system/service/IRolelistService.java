package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.entity.Rolelist;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-08-17
 */
public interface IRolelistService extends IService<Rolelist> {

    Set<String> getUserRoles(String username);
}
