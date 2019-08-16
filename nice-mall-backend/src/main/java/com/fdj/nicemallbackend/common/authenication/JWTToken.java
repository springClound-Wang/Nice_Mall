package com.fdj.nicemallbackend.common.authenication;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Classname JWTToken
 * @Description TODO
 * @Date 19-8-14 下午5:29
 * @Created by xns
 */
@Data
public class JWTToken implements AuthenticationToken {
    private static final long serialVersionUID = 1282057025599826155L;

    private String token;

    private String exipreAt;

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String exipreAt) {
        this.token = token;
        this.exipreAt = exipreAt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
