package com.ruichen.restful.config.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName  JwtToken
 * @Description  TODO
 * @Date  2019/7/1 21:42
 * @author  lixueyun
 * @version  V1.0
 */
public class JwtToken implements AuthenticationToken {

    /**
     * Token
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
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
