package com.fdj.nicemallbackend.system.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Items;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.Goods;
import com.fdj.nicemallbackend.system.service.IGoodsService;
import com.fdj.nicemallbackend.system.service.IMixService;
import com.fdj.nicemallbackend.system.service.ISearchService;
import com.fdj.nicemallbackend.system.service.ITypeGoodsService;
import com.fdj.nicemallbackend.system.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Classname HomeController
 * @Description TODO
 * @Date 19-9-3 下午8:58
 * @Created by xns
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    ITypeGoodsService iTypeGoodsService;

    @Autowired
    IGoodsService goodsService;

    @Autowired
    IMixService iMixService;

    @Autowired
    ISearchService iSearchService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 获取商品分类
     * @return
     */
    @GetMapping("/sort")
    public Result getSort(){
        Result result = iTypeGoodsService.getSort();
        return result;
    }

    /**
     * 搜索查询
     * @param field
     * @return
     */
    @GetMapping("/{field}")
    public Result fuzzyQuery(@PathVariable String field){
        List<Items> items = iSearchService.search(field);
        if(items.isEmpty()) {
            return new Result().fail("查询失败,无对应的数据!!!");
        }
        List<Findgoods> goods = new ArrayList<>();
        items.forEach(items1 -> {
            Findgoods findgoods1 = null;
            try {
                findgoods1 = MAPPER.readValue(items1.getFindGoods(), Findgoods.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            goods.add(findgoods1);
        });
        return new Result().success(goods, "查询成功!!!");
    }

    /**
     * 类型点击查询
     * @param typefield
     * @return
     */
    @GetMapping("/type/{typefield}")
    public Result SortQuery(@PathVariable String typefield){
        System.out.println(typefield);
        Set<Findgoods> goods = goodsService.findBySortType(typefield);
        if(goods.isEmpty()) {
            return new Result().fail("查询失败,无对应的数据!!!");
        }
        else{
            return new Result().success(goods, "查询成功!!!");
        }
    }

    /**
     * 首页中根据分类获取数据
     * @param type
     * @return
     */
    @GetMapping("/sort/{type}")
    public Result typeQuery(@PathVariable String type){
        Result res = iTypeGoodsService.getPoupularSort(type);
        if(res.isStatus()) {
            Map<String,Object> map = (Map<String,Object>)res.getData();
            List<Findgoods> typeGoods = iTypeGoodsService.getSortGoods(type);
            if (typeGoods.isEmpty()) {
                return new Result().fail(map,"有主图片,但是无对应的货物数据!");
            }
            map.put("goods",typeGoods);
            return new Result().success(map, "查询成功!");
        }
        else{
            return new Result().fail("未查询到主图片信息!");
        }
    }

    /**
     * 获取某个商品的详情
     * @param goodsId
     * @return
     */
    @GetMapping("/showone/{goodsId}")
    public Result showGoodDetail(@PathVariable Long goodsId){
        Result res = goodsService.showOneDetail(goodsId);
        return res;
    }

    /**
     * 获取首页的三组大数据
     * @return
     */
    @GetMapping("/")
    public Result homePage(){
        Result result = iMixService.getHomePage();
        return result;
    }
}
