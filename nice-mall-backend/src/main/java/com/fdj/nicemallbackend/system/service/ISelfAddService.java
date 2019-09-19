package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.TypeEntry;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-09-18
 */
public interface ISelfAddService extends IService<TypeEntry> {

    /**
     * 用测试工具添加分类入口
     * @param typeEntry
     * @return
     */
    Result addEntry(TypeEntry typeEntry);
}
