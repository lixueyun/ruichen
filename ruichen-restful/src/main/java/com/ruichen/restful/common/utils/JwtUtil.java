package com.ruichen.restful.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruichen.restful.common.constant.ShiroConstant;
import com.ruichen.restful.common.enums.ErrorCodeEnum;
import com.ruichen.restful.common.exception.ShiroSpecialException;
import com.ruichen.restful.common.response.BaseResponse;
import com.ruichen.restful.common.response.BaseResponseBuilder;
import com.ruichen.restful.config.shiro.ShiroProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @ClassName  JwtUtil
 * @Description  jwt工具类
 * @Date  2019/7/1 21:49
 * @author  lixueyun
 * @version  V1.0
 */
@Slf4j
@Component
public class JwtUtil {

    /**
     * @methodName  verify
     * @description  校验token是否正确
     * @param token
     * @author  lixueyun
     * @Date  2019/7/1 22:04
     * @return  boolean
     */
    public static boolean verify(String token) {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, ShiroConstant.ACCOUNT) + Base64ConvertUtil.decode(ShiroProperties.ENCRYPT_JWT_KEY);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("JWTToken认证解密出现异常\n" + e.getMessage());
            throw new AuthenticationException(ErrorCodeEnum.E101004.getText());
        }
    }

    /**
     * @methodName  getClaim
     * @description  获得Token中的信息无需secret解密也能获得
     * @param token
     * @param claim
     * @author  lixueyun
     * @Date  2019/7/1 21:58
     * @return  java.lang.String
     */
    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            // 只能输出String类型，如果是其他类型返回null
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            log.error("解密Token中的公共信息出现JWTDecodeException异常\n" + e.getMessage());
            throw new ShiroSpecialException(ErrorCodeEnum.E101004, ErrorCodeEnum.E101004.getText());
        }
    }

    /**
     * @methodName  sign
     * @description  生成签名
     * @param account
     * @param currentTimeMillis
     * @author  lixueyun
     * @Date  2019/7/1 22:06
     * @return  java.lang.String  返回加密的Token
     */
    public static String sign(String account, String currentTimeMillis) {
        try {
            // 帐号加JWT私钥加密
            String secret = account + Base64ConvertUtil.decode(ShiroProperties.ENCRYPT_JWT_KEY);
            // 此处过期时间是以毫秒为单位，所以乘以1000
            Date date = new Date(System.currentTimeMillis() + ShiroProperties.ACCESS_TOKENEXPIRE_TIME * 1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            return JWT.create()
                    .withClaim("account", account)
                    .withClaim("currentTimeMillis", currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("JWTToken加密出现UnsupportedEncodingException异常\n" + e.getMessage());
            throw new ShiroSpecialException(ErrorCodeEnum.E101000, ErrorCodeEnum.E101000.getText());
        }
    }


    //返回信息 因为RestControllerAdvice捕获不到异常
    //在Filter没有进入到Controller如果直接抛异常，Spring全局异常解析捕获不到
    public static void responseMessage(ServletResponse response, Object value, String msg) {
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()) {
            BaseResponse dataResponse = new BaseResponseBuilder<>().fail(value, msg).build();
            ObjectMapper objectMapper = new ObjectMapper();
            String data = objectMapper.writeValueAsString(dataResponse);
            out.append(data);
        } catch (IOException e) {
            log.error("直接返回Response信息出现IOException异常\n", e.getMessage());
            throw new ShiroSpecialException(ErrorCodeEnum.E101000, ErrorCodeEnum.E101000.getText());
        }
    }
}
