package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.authenication.Jwt_Get;
import com.fdj.nicemallbackend.common.domain.TypeConsts;
import com.fdj.nicemallbackend.common.utils.OssuploadUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.dto.Spikes;
import com.fdj.nicemallbackend.system.dto.goodsList;
import com.fdj.nicemallbackend.system.dto.orderDto;
import com.fdj.nicemallbackend.system.entity.PopularSort;
import com.fdj.nicemallbackend.system.entity.SortImage;
import com.fdj.nicemallbackend.system.entity.Spike;
import com.fdj.nicemallbackend.system.service.*;
import com.fdj.nicemallbackend.system.service.impl.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    ISpikeService iSpikeService;

    @Autowired
    IOrderService iOrderService;

    @Autowired
    Jwt_Get jwt_get;

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


    /**
     * 添加疯抢商品
     * @param map
     * @return
     */
    @PostMapping("/spike")
    public Result addSpike(@RequestBody Map<String,List<Spikes>> map){
        List<Spikes> lists = map.get("modifyGoodsList");
        Result result = iSpikeService.addSpikes(lists);
        return result;
    }


    /**
     * 修改商品信息时候，获取商品的部分信息
     * @param goodsId
     * @return
     */
    @GetMapping("/spike")
    public Result getNew(@RequestParam (value = "goodsId") Long goodsId){
        Result result = iSpikeService.getPartNews(goodsId);
        return result;
    }

    /**
     * 商家获取某状态的订单
     * @param request
     * @return
     */
    @GetMapping("/status")
    public Result getAllUnCertain(HttpServletRequest request,@RequestParam Integer orderStatus){
        Long userId = jwt_get.getUser(request);
        List <orderDto> orders = iOrderService.getOneStatusOrders(userId,orderStatus);
        if(CollectionUtils.isEmpty(orders)){
            return new Result().fail(null,"暂时没有数据哟");
        }
        return new Result().success(orders,"查询成功!");
    }

    /**
     * 商家发货修改状态
     */
    @PutMapping("/ship")
    public Result shipGoods(@RequestBody Map<String,Object> map){
        String res = iOrderService.updateListOrderStatus((List<String>)map.get("orderId"),Integer.parseInt((String)map.get("orderStatus")));
        if(res == null){
            return new Result().fail("操作失败！");
        }
        else{
            return new Result().success(res,"发货成功");
        }
    }
}