package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.PopularSort;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fdj.nicemallbackend.system.entity.SortImage;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-09-08
 */
public interface IPopularSortService extends IService<PopularSort> {

    /**
     * 保存热门分类的逻辑函数
     */
    Result saveData(Map<String,Object> map);
}
