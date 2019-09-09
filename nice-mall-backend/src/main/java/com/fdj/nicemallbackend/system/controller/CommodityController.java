package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.domain.TypeConsts;
import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.PopularSort;
import com.fdj.nicemallbackend.system.entity.SortImage;
import com.fdj.nicemallbackend.system.service.IGoodsService;
import com.fdj.nicemallbackend.system.service.IPopularSortService;
import com.fdj.nicemallbackend.system.service.ITypeGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Classname CommodityController
 * @Description TODO
 * @Date 19-8-30 上午11:10
 * @Created by xns
 */
@RestController
public class CommodityController {


    @Autowired
    IGoodsService iGoodsService;

    @Autowired
    ITypeGoodsService iTypeGoodsService;

    @Autowired
    IPopularSortService iPopularSortService;


    /**
     * 添加商品
     * @param map
     * @return
     */
    @PostMapping("/addgoods")
    public Result Addcommdity(@RequestBody Map<String,Object> map){
        List type = (List) map.get("goodsType");
        Result result = new Result();
        Map<String,Integer> listId = iTypeGoodsService.recoderType(type);
        if(TypeConsts.TYPE_CLOTHES.equals(type.get(0))){
            result = iGoodsService.saveToclothes(map);
        }
        else if(TypeConsts.TYPE_SHOES.equals(type.get(0))){
            result = iGoodsService.saveToshoes(map);
        }
        else if(TypeConsts.TYPE_PACKAGE.equals(type.get(0))){
            result = iGoodsService.saveTopackage(map);
        }
        else if(TypeConsts.TYPE_ELECTRONIC.equals(type.get(0))){
            result = iGoodsService.saveToelectr(map);
        }
        if(result.isStatus()){
            iTypeGoodsService.linked((Long)result.getData(),listId);
        }
        return result;
    }

    /**
     * 添加热门分类
     * @param map
     * @return
     */
    @PostMapping("/addpopular")
    public Result addPopularSort(@RequestBody Map<String,Object> map){
        Result result = iPopularSortService.saveData(map);
        return result;
    }
}
