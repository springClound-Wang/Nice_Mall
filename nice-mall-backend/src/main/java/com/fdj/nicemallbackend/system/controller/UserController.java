package com.fdj.nicemallbackend.system.controller;


import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xns
 * @since 2019-08-16
 */
@RestController
public class UserController {

    @Autowired
    IUserService iUserService;

    @GetMapping("/user")
    public Result selectAlluser(){
        return new Result().success(iUserService.getAllUser());
    }

}

