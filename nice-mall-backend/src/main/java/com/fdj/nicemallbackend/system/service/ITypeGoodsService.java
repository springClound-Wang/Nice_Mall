package com.fdj.nicemallbackend.system.service;

import com.fdj.nicemallbackend.system.entity.TypeGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xns
 * @since 2019-09-01
 */
public interface ITypeGoodsService extends IService<TypeGoods> {

    void recoderTye(List type);
}
