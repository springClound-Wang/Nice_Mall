package com.fdj.nicemallbackend.common.authenication;

import com.fdj.nicemallbackend.common.utils.TokenUtil;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname Jwt_Get
 * @Description TODO
 * @Date 19-10-17 下午9:20
 * @Created by xns
 */
@Component
public class Jwt_Get {

    private static final String TOKEN = "Authorization";

    @Autowired
    private static IUserService iUserService;

    /**
     * 返回当前登录用户名称或者手机号
     * @param request
     * @return
     */
    public static Long getUser(HttpServletRequest request){
        String token = request.getHeader(TOKEN);
        /**
         * 解密token
         */
        JWTToken jwtToken = new JWTToken(TokenUtil.decryptToken(token));
        String loginperson = JWTUtil.getLoginperson(token);
        User user = iUserService.getUser(loginperson);
        if(user == null){
            user = iUserService.getUserByphone(loginperson);
        }
        return user.getUserId();
    }
}
