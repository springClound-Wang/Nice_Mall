package com.fdj.nicemallbackend.common.authenication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fdj.nicemallbackend.common.properties.ShiroProperties;
import com.fdj.nicemallbackend.common.utils.SpringContextUtil;
import com.fdj.nicemallbackend.common.utils.TokenUtil;
import com.fdj.nicemallbackend.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Classname JWTUtil
 * @Description TODO
 * @Date 19-8-14 下午5:29
 * @Created by xns
 */
@Slf4j
public class JWTUtil {
    private static final long EXPIRE_TIME = SpringContextUtil.getBean(ShiroProperties.class).getJwtTimeOut() * 1000;


    /**
     * 校验 token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */

    public static boolean verify(String token, String loginperson, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            /**
             * 如果loginperson验证不一致就会报错
             */
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("loginperson", loginperson)
                    .build();//可重用的验证程序实例
            verifier.verify(token);
            log.info("token is valid");
            return true;
        } catch (Exception e) {
            log.info("token is invalid{}", e.getMessage());
            return false;
        }
    }


    /**
     * 从 token中获取用户
     * @return token中包含的用户
     */

    public static String getLoginperson(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginperson").asString();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }



    /**
     * 生成 token
     *
     * @param loginperson 登录名称
     * @param secret   用户的密码
     * @return token
     * JSON Web Tokens由dot（.）分隔的三个部分组成，它们是：
     * <p>
     * 头
     * 有效载荷
     * 签名
     * 因此，JWT通常如下所示。
     * <p>
     * xxxxx.yyyyy.zzzzz
     **/


    public static String sign(String loginperson, String secret) {
        String token ="";
        try {
            /*username = StringUtils.lowerCase(username);*/
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withClaim("loginperson", loginperson)
                    .withExpiresAt(date)
                    .sign(algorithm);
            System.out.println("token***$$$ = "+token);
            return token;
        } catch (Exception e) {
            log.error("error：{}", e);
            return null;
        }
    }
}
