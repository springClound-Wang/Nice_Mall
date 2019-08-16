package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Logincontroller
 * @Description TODO
 * @Date 19-8-13 下午9:22
 * @Created by xns
 */
@RestController
public class Logincontroller {

    @Autowired
    private IUserService userService;

    /**
     * 请求发送验证码
     * @param telephone
     * @return
     */
    @PostMapping("/register/verifycode")
    public Map<String,Object> verify(String telephone){
        Map<String,Object> res = new HashMap<>();
        /**
         * 发送短信验证码（暂留)
         */
        return res;
    }


    /**
     * 验证验证码并保存信息
     * @throws Exception
     */
    @ResponseStatus
    @PostMapping("/register")
    public Result register(@RequestBody Map<String,String> map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        Boolean flag = userService.regist(map.get("telephone"),map.get("password"),map.get("code"));
        if(flag == true){
            return new Result().success("注册成功!!!");
        }
        else{
            return new Result().fail("验证码错误");
        }
    }
}
