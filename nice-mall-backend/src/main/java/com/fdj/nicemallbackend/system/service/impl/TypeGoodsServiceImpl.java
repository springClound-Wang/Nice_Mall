package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.Sort2;
import com.fdj.nicemallbackend.system.dto.Sort3;
import com.fdj.nicemallbackend.system.entity.Sort;
import com.fdj.nicemallbackend.system.entity.SortListName;
import com.fdj.nicemallbackend.system.entity.SortListType;
import com.fdj.nicemallbackend.system.entity.TypeGoods;
import com.fdj.nicemallbackend.system.mapper.SortListNameMapper;
import com.fdj.nicemallbackend.system.mapper.SortListTypeMapper;
import com.fdj.nicemallbackend.system.mapper.SortMapper;
import com.fdj.nicemallbackend.system.mapper.TypeGoodsMapper;
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
                listId.put("sortListTypeId", sortListType1.getSortListId());
            } catch (DuplicateKeyException e) {
                sortListType = sortListTypeMapper.selectId((String) type.get(2));
                listId.put("sortListTypeId", sortListType.getSortListId());
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
                Sort3 sort3 = new Sort3(sortListNames.get(j).getSortListName(),goodsAll);
                goodsList.add(sort3);
            }
            Sort2 sort2 = new Sort2(sorts.get(i).getSortName(),goodsList);
            goodsTypeList.add(sort2);
        }
       /* Map<String,List<Sort2>> map = new HashMap<>();
        map.put("goodsTypeList",goodsTypeList);*/
        return new Result().success(goodsTypeList,"成功!!");
    }
}
