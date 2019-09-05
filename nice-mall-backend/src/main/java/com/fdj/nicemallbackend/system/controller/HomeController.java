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

    @GetMapping("/{field}")
    public Result fuzzyQuery(@PathVariable String field){
        List<Findgoods> goods = goodsService.findByField(field);
        if(goods!=null) {
            return new Result().success(goods, "查询成功!!!");
        }
        else{
            return new Result().success("查询失败,无对应的数据!!!");
        }
    }

}
