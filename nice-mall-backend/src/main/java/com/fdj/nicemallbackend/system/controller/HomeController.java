package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.system.dto.Findgoods;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.Goods;
import com.fdj.nicemallbackend.system.service.IGoodsService;
import com.fdj.nicemallbackend.system.service.ITypeGoodsService;
import com.fdj.nicemallbackend.system.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        Set<Findgoods> goods = goodsService.findByField(field);
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

//    @GetMapping("/showone/{goodsId}")
//    public Result showGoodDetail(@PathVariable Integer goodsId){
//         = goodsService.showOneDetail(goodsId);
//        return null;
//    }
}
