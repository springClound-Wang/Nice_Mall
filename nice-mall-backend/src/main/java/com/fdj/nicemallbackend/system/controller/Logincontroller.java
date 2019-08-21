package com.fdj.nicemallbackend.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fdj.nicemallbackend.common.authenication.JWTToken;
import com.fdj.nicemallbackend.common.authenication.JWTUtil;
import com.fdj.nicemallbackend.common.domain.VerifyConsts;
import com.fdj.nicemallbackend.common.properties.ShiroProperties;
import com.fdj.nicemallbackend.common.utils.DateUtil;
import com.fdj.nicemallbackend.common.utils.IPUtil;
import com.fdj.nicemallbackend.common.utils.RedisUtil;
import com.fdj.nicemallbackend.common.utils.TokenUtil;
import com.fdj.nicemallbackend.system.dto.Result;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private RedisUtil redisUtil;

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
    @PostMapping("/register")
    public Result register(@RequestBody Map<String,String> map) throws Exception {
        Map<String,Object> result = new HashMap<>();
        System.out.println(map.get("telephone")+"**"+map.get("password")+"*****"+map.get("code")+"  ");
        User user = userService.getUserByphone(map.get("telephone"));
        if(user!=null){
            return new Result().fail("此电话已经被注册咯！换一个吧!");
        }
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
    @PostMapping("/login/phone")
    public Result telephonelogin(@RequestBody Map<String,String> map,HttpServletRequest request) throws Exception {
        String telephone = map.get("telephone");
        String password = map.get("password");
        System.out.println(password);
        User user = userService.getUserByphone(telephone);
        if (user == null) {
            return new Result().fail("电话号不存在!!!");
        }
        if(!user.getUserPassword().equals(password)){
            return new Result().fail("密码错误!!!");
        }
        //获取header.payload.signature形式的token,并进行加密。
        String token = TokenUtil.encryptToken(JWTUtil.sign(telephone,password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(shiroProperties.getJwtTimeOut());
        JWTToken jwtToken = new JWTToken(token,expireTime.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss")));
        Map<String,Object> resInfo = new HashMap<>();
        resInfo.put("userid",user.getUserId());
        resInfo.put("username",user.getUserName());
        resInfo.put("token",token);
        resInfo.put("exipreTime",jwtToken.getExipreAt());
        this.savetoRedis(jwtToken,request);
        return new Result().success(resInfo,"登录成功!!!");
    }

    /**
     *name+password登录
     */
    @PostMapping("/login/name")
    public Result userNamelogin(@RequestBody Map<String,String> map,HttpServletRequest request) throws Exception {
        String username = map.get("username");
        String password = map.get("password");
        User user = userService.getUser(username);
        if (user == null) {
            return new Result().fail("用户名不存在!!!");
        }
        if(!user.getUserPassword().equals(password)){
            return new Result().fail("密码错误!!!");
        }
        String token = TokenUtil.encryptToken(JWTUtil.sign(username,password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(shiroProperties.getJwtTimeOut());
        JWTToken jwtToken = new JWTToken(token,expireTime.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss")));
        Map<String,Object> resInfo = new HashMap<>();
        resInfo.put("userid",user.getUserId());
        resInfo.put("username",user.getUserName());
        resInfo.put("token",token);
        resInfo.put("exipreTime",jwtToken.getExipreAt());
        this.savetoRedis(jwtToken,request);
        return new Result().success(resInfo,"登录成功!!!");
    }

    /**
     * 登出
     * @param id
     * @return
     */
    @DeleteMapping("/logout")
    public Result logoutByid(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String ip = IPUtil.getIpAddr(request);
        String now = DateUtil.formatFullTime(LocalDateTime.now());
        if(redisUtil.hasKey(VerifyConsts.TOKEN_CACHE_PREFIX+token+ StringPool.DOT+ip)) {
            redisUtil.del(VerifyConsts.TOKEN_CACHE_PREFIX + token + StringPool.DOT + ip);
            return new Result().success("登出成功!!!");
        }
        else{
            return new Result().fail("您当前未登录，您看到的是假象，请刷新!!!");
        }
    }


    private void savetoRedis(JWTToken token, HttpServletRequest request) throws Exception{
        String ip = IPUtil.getIpAddr(request);
        redisUtil.set(VerifyConsts.TOKEN_CACHE_PREFIX+token.getToken()+ StringPool.DOT+ip,token.getToken(),shiroProperties.getJwtTimeOut());
    }
}
