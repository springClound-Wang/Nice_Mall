package com.fdj.nicemallbackend.common.authenication;

import com.fdj.nicemallbackend.common.utils.HttpContextUtil;
import com.fdj.nicemallbackend.common.utils.IPUtil;
import com.fdj.nicemallbackend.common.utils.RedisUtil;
import com.fdj.nicemallbackend.common.utils.TokenUtil;
import com.fdj.nicemallbackend.system.entity.User;
import com.fdj.nicemallbackend.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;

import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import com.fdj.nicemallbackend.common.domain.VerifyConsts;

import java.util.Set;


/**
 * @Classname ShiroRealm
 * @Description TODO
 * @Date 19-8-14 下午5:30
 * @Created by xns
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


     /** 授权模块，获取用户角色和权限
     *
     * @param token token
     * @return AuthorizationInfo 权限信息
      */


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        String username = JWTUtil.getUsername(token.toString());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        /*// 获取用户角色集
        Set<String> roleSet = userManager.getUserRoles(username);
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        Set<String> permissionSet = userManager.getUserPermissions(username);
        simpleAuthorizationInfo.setStringPermissions(permissionSet);*/
        return simpleAuthorizationInfo;
    }


     /** 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     * */


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();

        // 从 redis里获取这个 token
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);

        String encryptToken = TokenUtil.encryptToken(token);
        String encryptTokenInRedis = null;
        try {
            /**
             * 改过
             */
            encryptTokenInRedis = (String) redisUtil.get(VerifyConsts.TOKEN_CACHE_PREFIX + encryptToken + "." + ip);
        } catch (Exception ignore) {
        }
        // 如果找不到，说明已经失效
        if (StringUtils.isBlank(encryptTokenInRedis)) {
            throw new AuthenticationException("token已经过期");
        }

        String username = JWTUtil.getUsername(token);

        if (StringUtils.isBlank(username)) {
            throw new AuthenticationException("token校验不通过");
        }

        // 通过用户名查询用户信息
        User user = iUserService.getUser(username);

        if (user == null) {
            throw new AuthenticationException("用户名或密码错误");
        }
        if (!JWTUtil.verify(token, username, user.getUserPassword())) {
            throw new AuthenticationException("token校验不通过");
        }
        return new SimpleAuthenticationInfo(token, token, "nice_shiro_realm");
    }
}
