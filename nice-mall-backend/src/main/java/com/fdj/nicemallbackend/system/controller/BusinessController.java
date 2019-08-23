package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.Business;
import com.fdj.nicemallbackend.system.service.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xns
 * @since 2019-08-22
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    IBusinessService iBusinessService;

    @PostMapping("/createshop")
    public Result createShop(@RequestBody Business business){
        System.out.println(business.getBusinessAddress()+"****"+business.getBusinessIdentityNumber());
        Result result = iBusinessService.registerShop(business);
        return result;
    }
}

