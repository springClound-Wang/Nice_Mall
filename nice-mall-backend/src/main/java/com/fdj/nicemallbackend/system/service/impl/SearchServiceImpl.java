package com.fdj.nicemallbackend.system.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdj.nicemallbackend.system.dto.Items;
import com.fdj.nicemallbackend.system.entity.*;
import com.fdj.nicemallbackend.system.mapper.*;
import com.fdj.nicemallbackend.system.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname SearchServiceImpl
 * @Description TODO
 * @Date 19-10-30 下午5:11
 * @Created by xns
 */
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    TypeGoodsMapper typeGoodsMapper;

    @Autowired
    SortMapper sortMapper;

    @Autowired
    SortListNameMapper sortListNameMapper;

    @Autowired
    SortListTypeMapper sortListTypeMapper;

    @Autowired
    GoodsMapper goodsMapper;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public Items buildGoods(Goods goods) throws JsonProcessingException {
        Items items = new Items();

        TypeGoods typeGoods = typeGoodsMapper.selectByGoodsId(goods.getGoodsId());
        Sort sort = sortMapper.selectBySortId(typeGoods.getSortId());
        SortListName sortListName = sortListNameMapper.selectByNid(typeGoods.getSortListNameId());
        SortListType sortListType = sortListTypeMapper.selectByTid(typeGoods.getSortListTypeId());
        items.setId(goods.getGoodsId());
        items.setSid1(typeGoods.getSortId());
        items.setSid2(typeGoods.getSortListNameId());
        items.setSid3(typeGoods.getSortListTypeId());
        items.setBrand(goods.getGoodsBrand());
        items.setAll(goods.getGoodsName()+" "+goods.getGoodsDesc()+" "+goods.getGoodsBrand()+" "+sort.getSortName()+" "+sort.getSortEnglishName()+" "+sortListName.getSortListName()+" "+sortListType.getSortListName());
        items.setPrice(goods.getGoodsCurPrice());
        items.setFindGoods(MAPPER.writeValueAsString(goodsMapper.findById(goods.getGoodsId())));
        return items;
    }
}
