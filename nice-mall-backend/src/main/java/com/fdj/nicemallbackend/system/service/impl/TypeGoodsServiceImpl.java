package com.fdj.nicemallbackend.system.service.impl;

import com.fdj.nicemallbackend.system.dto.Result;
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
     * @param type
     * @return
     */
    @Override
    public Map<String, Integer> recoderType(List type) {
        Map<String, Integer> listId = new HashMap<>();
        SortListName sortListName = null;
        SortListName sortListName1 = null;
        SortListType sortListType=null;
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
                    listId.put("sortListNameId",sortListName.getSortListId());
                    sortListType1 = new SortListType(sortListName.getSortListId(), (String) type.get(2));
                } else {
                    listId.put("sortListNameId",sortListName1.getSortListId());
                    sortListType1 = new SortListType(sortListName1.getSortListId(), (String) type.get(2));
                }
                sortListTypeMapper.save(sortListType1);
                listId.put("sortListTypeId",sortListType1.getSortListId());
            } catch (DuplicateKeyException e) {
                sortListType = sortListTypeMapper.selectId((String)type.get(2));
                listId.put("sortListTypeId",sortListType.getSortListId());
                log.error("此三阶类型已经添加过");
            }
        }
        return listId;
    }

    /**
     * 将货物和商品类型联系起来
     * @param goodsId
     * @param listId
     */
    @Override
    public void linked(Long goodsId, Map<String, Integer> listId) {
        TypeGoods typeGoods = new TypeGoods(listId.get("sortId"),listId.get("sortListNameId"),listId.get("sortListTypeId"),goodsId);
        typeGoodsMapper.save(typeGoods);
    }

    @Override
    public Result getSort() {
        return new Result().success("成功!!");
    }
}
