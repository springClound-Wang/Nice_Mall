package com.fdj.nicemallbackend.system.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Items;
import com.fdj.nicemallbackend.system.entity.*;
import com.fdj.nicemallbackend.system.mapper.*;
import com.fdj.nicemallbackend.system.repository.ItemRepository;
import com.fdj.nicemallbackend.system.service.ISearchService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    ItemRepository itemRepository;

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
        items.setAll(goods.getGoodsName()+" "+goods.getGoodsDesc());
        items.setPrice(goods.getGoodsCurPrice());
        items.setFindGoods(MAPPER.writeValueAsString(goodsMapper.findById(goods.getGoodsId())));
        return items;
    }

    @Override
    public List<Items> search(String field) {
        if(StringUtils.isBlank(field)){
            return null;
        }
        /**
         * 自定义查询构建器
         */
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        /**
         * 添加查询条件
         */
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("all",field).operator(Operator.AND));
        //添加结果集过滤
        nativeSearchQueryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"findGoods"},null));
        //执行查询获取结果集
        Page<Items> goods = this.itemRepository.search(nativeSearchQueryBuilder.build());
        return goods.getContent();
    }
}
