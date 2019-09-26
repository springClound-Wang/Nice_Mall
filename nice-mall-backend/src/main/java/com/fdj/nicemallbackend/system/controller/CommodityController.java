package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.domain.TypeConsts;
import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.goodsList;
import com.fdj.nicemallbackend.system.entity.PopularSort;
import com.fdj.nicemallbackend.system.entity.SortImage;
import com.fdj.nicemallbackend.system.service.IBusinessService;
import com.fdj.nicemallbackend.system.service.IGoodsService;
import com.fdj.nicemallbackend.system.service.IPopularSortService;
import com.fdj.nicemallbackend.system.service.ITypeGoodsService;
import com.fdj.nicemallbackend.system.service.impl.BusinessServiceImpl;
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
@RequestMapping("/buss")
public class CommodityController {


    @Autowired
    IGoodsService iGoodsService;

    @Autowired
    ITypeGoodsService iTypeGoodsService;

    @Autowired
    IPopularSortService iPopularSortService;

    @Autowired
    IBusinessService ibusinessService;


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

    /**
     * 店家获取自己店铺的所有数据
     * @param id
     * @return
     */
    @GetMapping("/getgoods/{id}")
    public Result getGoods(@PathVariable Long id){
        List<goodsList> goodsLists= ibusinessService.getGoodsList(id);
        if(goodsLists.isEmpty()){
            return new Result().fail("店铺暂时没有商品，快去添加吧!!");
        }
        else{
            return new Result().success(goodsLists,"数据查询成功!!");
        }
    }
}
