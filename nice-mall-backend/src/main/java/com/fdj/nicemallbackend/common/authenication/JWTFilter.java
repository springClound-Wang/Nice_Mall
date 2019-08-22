package com.fdj.nicemallbackend.common.authenication;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fdj.nicemallbackend.common.properties.ShiroProperties;
import com.fdj.nicemallbackend.common.utils.SpringContextUtil;
import com.fdj.nicemallbackend.common.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Classname JWTFilter
 * @Description TODO
 * @Date 19-8-14 下午5:29
 * @Created by xns
 */
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {
    private static final String TOKEN = "Authorization";

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        ShiroProperties shiroProperties = SpringContextUtil.getBean(ShiroProperties.class);
        String[] anonUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(shiroProperties.getAnonUrl(), StringPool.COMMA);

        boolean match = false;
        /**
         * 如果匹配到设置的免认证url,则放行不用验证token
         */
        for (String u : anonUrl) {
            System.out.println(u+"   *****  "+httpServletRequest.getRequestURI());
            if (pathMatcher.match(u, httpServletRequest.getRequestURI())) {
                match = true;
            }
        }
        if (match){ return true;}
        /**
         * 查看是否携带验证token,若携带，则验证token
         */
        if (isLoginAttempt(request, response)) {
            return executeLogin(request, response);
        }
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(TOKEN);
        return token != null;
    }

    /**
     * 验证token
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader(TOKEN);
        /**
         * 解密token
         */
        JWTToken jwtToken = new JWTToken(TokenUtil.decryptToken(token));
        try {
            getSubject(request, response).login(jwtToken);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With,Content-Type, Accept, Authorization,If-Modified-Since");
        httpServletResponse.setContentType("application/json");
        // 跨域时会首先发送一个 option请求，这里我们给 option请求返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean sendChallenge(ServletRequest request,ServletResponse response) {
        log.debug("Authentication required: sending 401 Authentication challenge response.");
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        final String message = "登录已失效，请重新登录!!!";
        try (PrintWriter out = httpResponse.getWriter()) {
            String responseJson = "{\"message\":\"" + message + "\"}";
            out.print(responseJson);
        } catch (IOException e) {
            log.error("sendChallenge error：", e);
        }
        return false;
    }
}
