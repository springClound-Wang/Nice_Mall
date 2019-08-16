package com.fdj.nicemallbackend.system.controller;

import com.fdj.nicemallbackend.common.authenication.JWTToken;
import com.fdj.nicemallbackend.common.authenication.JWTUtil;
import com.fdj.nicemallbackend.common.properties.ShiroProperties;
import com.fdj.nicemallbackend.common.utils.TokenUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private ShiroProperties shiroProperties;

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

    /**
     *telephone+password登录
     */
    @ResponseStatus
    @PostMapping("/login")
    public Result telephonelogin(@RequestBody Map<String,String> map){
        String telephone = map.get("telephone");
        String password = map.get("password");
        User user = userService.getUserByphone(telephone);
        if (user == null) {
            return new Result().fail("用户名不存在!!!");
        }
        if(!user.getUserPassword().equals(password)){
            return new Result().fail("密码错误!!!");
        }
        String token = TokenUtil.encryptToken(JWTUtil.sign(telephone,password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(shiroProperties.getJwtTimeOut());
        JWTToken jwtToken = new JWTToken(token,expireTime.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss")));
        Map<String,Object> resInfo = new HashMap<>();
        resInfo.put("userid",user.getUserId());
        resInfo.put("username",user.getUserName());
        resInfo.put("token",token);
        return new Result().success(resInfo);
    }
}
