package com.fdj.nicemallbackend.common.authenication;

import com.fdj.nicemallbackend.common.utils.TokenUtil;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname Jwt_Get
 * @Description TODO
 * @Date 19-10-17 下午9:20
 * @Created by xns
 */
@Slf4j
@Component
public class Jwt_Get {

    private static final String TOKEN = "Authorization";

    @Autowired
    private IUserService iUserService;

    /**
     * 返回当前登录用户名称或者手机号
     *
     * @param request
     * @return
     */
    public Long getUser(HttpServletRequest request) {
        String token1 = request.getHeader(TOKEN);
        /**
         * 解密token
         */
        String token = TokenUtil.decryptToken(token1);
        String loginperson = JWTUtil.getLoginperson(token);
        System.out.println(loginperson + "***&&&");
        if (iUserService.getUserByphone(loginperson) != null) {
            User user = iUserService.getUserByphone(loginperson);
//            System.out.println(user + "====");
            return user.getUserId();
        } else {
            User user = iUserService.getUser(loginperson);
//            System.out.println(user + "====");
            return user.getUserId();
        }
    }
}
