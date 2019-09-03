package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.service.ITypeGoodsService;
import com.fdj.nicemallbackend.system.service.impl.TypeGoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HomeController
 * @Description TODO
 * @Date 19-9-3 下午8:58
 * @Created by xns
 */
@RestController
public class HomeController {

    @Autowired
    ITypeGoodsService iTypeGoodsService;

    @GetMapping("/sort")
    public Result GetSort(){
        Result result = iTypeGoodsService.getSort();
        return result;
    }
}
