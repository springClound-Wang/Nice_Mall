package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.entity.TypeGoods;
import com.fdj.nicemallbackend.system.mapper.TypeGoodsMapper;
import com.fdj.nicemallbackend.system.service.ITypeGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-09-01
 */
@Service
public class TypeGoodsServiceImpl extends ServiceImpl<TypeGoodsMapper, TypeGoods> implements ITypeGoodsService {

    @Override
    public void recoderTye(List type) {

    }
}
