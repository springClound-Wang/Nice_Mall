package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.common.domain.SortConsts;
import com.fdj.nicemallbackend.system.dto.*;
import com.fdj.nicemallbackend.system.entity.*;
import com.fdj.nicemallbackend.system.mapper.*;
import com.fdj.nicemallbackend.system.service.IGoodsService;
import com.fdj.nicemallbackend.system.service.ITypeGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xns
 * @since 2019-09-02
 */
@Service
public class TypeGoodsServiceImpl extends ServiceImpl<TypeGoodsMapper, TypeGoods> implements ITypeGoodsService {

    @Autowired
    SortMapper sortMapper;

    @Autowired
    SortListNameMapper sortListNameMapper;

    @Autowired
    SortListTypeMapper sortListTypeMapper;

    @Autowired
    TypeGoodsMapper typeGoodsMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    SortImageMapper sortImageMapper;

    @Autowired
    PopularSortMapper popularSortMapper;



    /**
     * 保存各种类型
     *
     * @param type
     * @return
     */
    @Override
    public Map<String, Integer> recoderType(List type) {
        Map<String, Integer> listId = new HashMap<>();
        SortListName sortListName = null;
        SortListName sortListName1 = null;
        SortListType sortListType = null;
        SortListType sortListType1 = null;
        try {
            Sort sort = sortMapper.selectId((String) type.get(0));
            listId.put("sortId", sort.getSortId());
            sortListName1 = new SortListName(sort.getSortId(), (String) type.get(1));
            sortListNameMapper.save(sortListName1);
        } catch (DuplicateKeyException e) {
            sortListName = sortListNameMapper.selectId((String) type.get(1));
            log.error("此二阶级类型已经添加过");
        } finally {
            try {
                if (sortListName != null) {
                    listId.put("sortListNameId", sortListName.getSortListId());
                    sortListType1 = new SortListType(sortListName.getSortListId(), (String) type.get(2));
                } else {
                    listId.put("sortListNameId", sortListName1.getSortListId());
                    sortListType1 = new SortListType(sortListName1.getSortListId(), (String) type.get(2));
                }
                sortListTypeMapper.save(sortListType1);
                listId.put("sortListTypeId", sortListType1.getSortListTypeId());
            } catch (DuplicateKeyException e) {
                sortListType = sortListTypeMapper.selectId(listId.get("sortListNameId"), (String) type.get(2));
                listId.put("sortListTypeId", sortListType.getSortListTypeId());
                log.error("此三阶类型已经添加过");
            }
        }
        return listId;
    }

    /**
     * 将货物和商品类型联系起来
     *
     * @param goodsId
     * @param listId
     */
    @Override
    public void linked(Long goodsId, Map<String, Integer> listId) {
        TypeGoods typeGoods = new TypeGoods(listId.get("sortId"), listId.get("sortListNameId"), listId.get("sortListTypeId"), goodsId);
        typeGoodsMapper.save(typeGoods);
    }

    /**
     * 返回类型
     *
     * @return
     */
    @Override
    public Result getSort() {
        List<String> goodsAll = new ArrayList<>();
        List<Sort2> goodsTypeList = new ArrayList<>();
        List<Sort> sorts = sortMapper.selectAll();
        for (int i = 0; i < sorts.size(); i++) {
            List<SortListName> sortListNames = sortListNameMapper.selectBysortId(sorts.get(i).getSortId());
            List<Sort3> goodsList = new ArrayList<>();
            for (int j = 0; j < sortListNames.size(); j++) {
                goodsAll = sortListTypeMapper.selectBysortListNametId(sortListNames.get(j).getSortListId());
                Sort3 sort3 = new Sort3(sortListNames.get(j).getSortListName(), goodsAll);
                goodsList.add(sort3);
            }
            Sort2 sort2 = new Sort2(sorts.get(i).getSortName(), goodsList);
            goodsTypeList.add(sort2);
        }
        return new Result().success(goodsTypeList, "成功!!");
    }

    /**
     * 获取衣服类型的分类，男装和女装
     * @param temp
     * @return
     */
    public List<Findgoods> getClothes(String temp) {
        List<Findgoods> goods = new ArrayList<>();
        List<Integer> listNameIds = sortListNameMapper.selectByPartName(temp);
        if (!listNameIds.isEmpty()) {
            for (int i = 0; i < listNameIds.size(); i++) {
                List<Long> goodsIds = typeGoodsMapper.selectByListNameId(listNameIds.get(i));
                for (int j = 0; j < goodsIds.size(); j++) {
                    Findgoods goodsOne = goodsMapper.findById(goodsIds.get(j));
                    goods.add(goodsOne);
                }
            }
        }
        return goods;
    }

    /**
     * 获取鞋子，男鞋子和女鞋子
     * @param temp
     * @return
     */
    public List<Findgoods> getShoes(String temp) {
        List<Findgoods> goods = new ArrayList<>();
        List<Integer> listNameIds = sortListNameMapper.selectByPartNames(temp);
        if (!listNameIds.isEmpty()) {
            for (int i = 0; i < listNameIds.size(); i++) {
                List<Long> goodsIds = typeGoodsMapper.selectByListNameId(listNameIds.get(i));
                for (int j = 0; j < goodsIds.size(); j++) {
                    Findgoods goodsOne = goodsMapper.findById(goodsIds.get(j));
                    goods.add(goodsOne);
                }
            }
        }
        return goods;
    }



    /**
     * 按首页横条分类查询
     *
     * @param type
     * @return
     */
    @Override
    public List<Findgoods> getSortGoods(String type) {
        List<Findgoods> goods = new ArrayList<>();
        if (SortConsts.SORT_CLOTHES_WOMEN.equals(type)) {
            String temp = "女";
            goods = getClothes(temp);
        }
        if (SortConsts.SORT_CLOTHES_MEN.equals(type)) {
            String temp = "男";
            goods = getClothes(temp);
        }
        if (SortConsts.SORT_SHOES_WOMEN.equals(type)) {
            String temp = "女";
            goods = getShoes(temp);
        }
        if (SortConsts.SORT_SHOES_MEN.equals(type)) {
            String temp = "男";
            goods = getShoes(temp);
        }
        if (SortConsts.SORT_ELECTRONIC.equals(type) || SortConsts.SORT_PACKAGE.equals(type)) {
            Integer sortId = sortMapper.selectByName(type);
            List<Long> goodsIds = typeGoodsMapper.selectBySortId(sortId);
            for (int j = 0; j < goodsIds.size(); j++) {
                Findgoods goodsOne = goodsMapper.findById(goodsIds.get(j));
                goods.add(goodsOne);
            }
        }
        if (SortConsts.SORT_MATERNAL_BABY.equals(type)) {
            List<Integer> listNameIds = sortListNameMapper.selectByName(type);
            if (!listNameIds.isEmpty()) {
                for (int i = 0; i < listNameIds.size(); i++) {
                    List<Long> goodsIds = typeGoodsMapper.selectByListNameId(listNameIds.get(i));
                    for (int j = 0; j < goodsIds.size(); j++) {
                        Findgoods goodsOne = goodsMapper.findById(goodsIds.get(j));
                        goods.add(goodsOne);
                    }
                }
            }
        }
        return goods;
    }

    /**
     * 获取热门分类及主图片
     * @return
     */
    @Override
    public Result getPoupularSort(String type) {
        SortImage sortImage = sortImageMapper.selectByUploadType(type);
        if(sortImage == null){
            return new Result().fail("您所查询的大分类不存在!");
        }
        Map<String,Object> map = new HashMap<>();
        List<HotSort> hotSort = popularSortMapper.selectByImageMainId(sortImage.getImageMainId());
        map.put("hotHeadImg",sortImage.getImageMains());
        map.put("hotType",hotSort);
        return new Result().success(map,"查到");
    }
}
